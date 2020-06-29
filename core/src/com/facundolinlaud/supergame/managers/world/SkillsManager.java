package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.components.SkillsComponent;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.services.*;
import com.facundolinlaud.supergame.skills.SkillBlackboard;
import com.facundolinlaud.supergame.skills.SkillTask;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.SkillCooldownStartEvent;

import java.util.HashMap;
import java.util.Map;

import static com.facundolinlaud.supergame.utils.events.Messages.REJECTED_SKILL_DUE_TO_NOT_READY;
import static com.facundolinlaud.supergame.utils.events.Messages.SKILL_COOLDOWN_START;

public class SkillsManager extends PoolableTaskManager {
    private ComponentMapper<SkillsComponent> sm = Mappers.skills;

    private Map<Entity, Skill> castings;
    private Map<Entity, Runnable> onSkillsEnd;
    private SkillsFactory skillsFactory;
    private LightsManager lightsManager;
    private CameraManager cameraManager;
    private UIManager uiManager;
    private AgentService agentService;
    private CombatService combatService;
    private ParticlesService particlesService;
    private ProjectilesService projectilesService;
    private SkillService skillService;
    private MessageDispatcher messageDispatcher;

    public SkillsManager(SkillsFactory skillsFactory, LightsManager lightsManager, CameraManager cameraManager,
                         UIManager uiManager, AgentService agentService, CombatService combatService,
                         ParticlesService particlesService, ProjectilesService projectilesService) {
        this.castings = new HashMap();
        this.onSkillsEnd = new HashMap();
        this.skillsFactory = skillsFactory;
        this.lightsManager = lightsManager;
        this.cameraManager = cameraManager;
        this.uiManager = uiManager;
        this.agentService = agentService;
        this.combatService = combatService;
        this.particlesService = particlesService;
        this.projectilesService = projectilesService;
        this.skillService = new SkillService();
        this.messageDispatcher = MessageManager.getInstance();
    }

    public boolean requestCasting(Entity caster, String skillId, Runnable onSkillEnd) {
        onSkillsEnd.put(caster, onSkillEnd);
        return requestCasting(caster, skillId);
    }

    public boolean requestCasting(Entity caster, String skillId) {
        return requestCasting(caster, skillsFactory.get(skillId));
    }

    public boolean requestCasting(Entity caster, Skill skill) {
        if (isAlreadyCasting(caster)) return false;

        if (!skillService.canCast(caster, skill)) {
            messageDispatcher.dispatchMessage(REJECTED_SKILL_DUE_TO_NOT_READY);
            return false;
        }

        this.castings.put(caster, skill);

        SkillTask skillTask = skill.getSkillDto().build();
        cast(caster, skillTask);

        return true;
    }

    private void cast(Entity caster, SkillTask skillTask) {
        SkillBlackboard blackboard = new SkillBlackboard(caster, lightsManager, cameraManager, this,
                uiManager, agentService, combatService, particlesService, projectilesService);

        skillTask.setBlackboard(blackboard);
        skillTask.activate();
    }

    public void endCasting(Entity caster) {
        castings.remove(caster);

        if(onSkillsEnd.containsKey(caster)){
            onSkillsEnd.remove(caster).run();
        }
    }

    private boolean isAlreadyCasting(Entity caster) {
        return castings.containsKey(caster);
    }

    public void startCoolDown(Entity caster) {
        Skill skill = castings.get(caster);
        skillService.startCoolDown(caster, skill);
    }
}
