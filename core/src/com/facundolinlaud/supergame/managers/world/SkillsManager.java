package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.components.SkillsComponent;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;
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
    private SkillsFactory skillsFactory;
    private LightsManager lightsManager;
    private CameraManager cameraManager;
    private UIManager uiManager;
    private AgentService agentService;
    private CombatService combatService;
    private ParticlesService particlesService;
    private ProjectilesService projectilesService;
    private MessageDispatcher messageDispatcher;

    public SkillsManager(SkillsFactory skillsFactory, LightsManager lightsManager, CameraManager cameraManager,
                         UIManager uiManager, AgentService agentService, CombatService combatService,
                         ParticlesService particlesService, ProjectilesService projectilesService) {
        this.castings = new HashMap();
        this.skillsFactory = skillsFactory;
        this.lightsManager = lightsManager;
        this.cameraManager = cameraManager;
        this.uiManager = uiManager;
        this.agentService = agentService;
        this.combatService = combatService;
        this.particlesService = particlesService;
        this.projectilesService = projectilesService;
        this.messageDispatcher = MessageManager.getInstance();
    }

    public boolean requestCasting(Entity caster, String skillId) {
        return requestCasting(caster, skillsFactory.get(skillId));
    }

    public boolean requestCasting(Entity caster, Skill skill) {
        if (isAlreadyCasting(caster)) return false;

        if (!canCast(caster, skill)) {
            messageDispatcher.dispatchMessage(REJECTED_SKILL_DUE_TO_NOT_READY);
            return false;
        }

        this.castings.put(caster, skill);

        SkillTask skillTask = skill.getSkillDto().build();
        cast(caster, skillTask);

        return true;
    }

    private void cast(Entity caster, SkillTask skillTask) {
        SkillBlackboard blackboard = new SkillBlackboard(caster, lightsManager, cameraManager, uiManager, agentService,
                combatService, particlesService, projectilesService, this);

        skillTask.setBlackboard(blackboard);
        skillTask.activate();
    }

    public void endCasting(Entity caster) {
        castings.remove(caster);
    }

    private boolean isAlreadyCasting(Entity caster) {
        return castings.containsKey(caster);
    }

    private boolean canCast(Entity caster, Skill skill) {
        SkillsComponent skillsComponent = sm.get(caster);

        boolean isCoolingDown = skillsComponent.isCoolingDown(skill);
        boolean hasSkill = skillsComponent.has(skill);

        return !isCoolingDown && hasSkill;
    }

    public void startCoolDown(Entity caster) {
        Skill skill = castings.get(caster);

        SkillsComponent skillsComponent = sm.get(caster);
        skillsComponent.startCoolDown(skill);

        SkillCooldownStartEvent event = new SkillCooldownStartEvent(caster, skill);
        messageDispatcher.dispatchMessage(SKILL_COOLDOWN_START, event);
    }
}
