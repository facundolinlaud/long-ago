package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.services.AgentsService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;
import com.facundolinlaud.supergame.skills.SkillBlackboard;
import com.facundolinlaud.supergame.skills.SkillTask;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SkillsManager extends PoolableTaskManager {
    private Set<Entity> casters;
    private SkillsFactory factory;
    private LightsManager lightsManager;
    private CameraManager cameraManager;
    private UIManager uiManager;
    private AgentsService agentsService;
    private CombatService combatService;
    private ParticlesService particlesService;
    private ProjectilesService projectilesService;

    public SkillsManager(SkillsFactory factory, LightsManager lightsManager, CameraManager cameraManager,
                         UIManager uiManager, AgentsService agentsService, CombatService combatService,
                         ParticlesService particlesService, ProjectilesService projectilesService) {
        this.factory = factory;
        this.casters = new HashSet();
        this.lightsManager = lightsManager;
        this.cameraManager = cameraManager;
        this.uiManager = uiManager;
        this.agentsService = agentsService;
        this.combatService = combatService;
        this.particlesService = particlesService;
        this.projectilesService = projectilesService;
    }

    public void requestCasting(Entity caster, Skill skill) {
        if (casters.contains(caster)) {
            return;
        }

        this.casters.add(caster);
        SkillTask skillTask = skill.getSkillDto().build();
        cast(caster, skillTask);
    }

    private void cast(Entity caster, SkillTask skillTask) {
        SkillBlackboard skillBlackboard = new SkillBlackboard(caster, this, lightsManager, cameraManager,
                uiManager, agentsService, combatService, particlesService, projectilesService);

        skillTask.setBlackboard(skillBlackboard);
        skillTask.setStack(new Stack());
        skillTask.activate();
    }

    public void endCasting(Entity caster) {
        this.casters.remove(caster);
    }
}
