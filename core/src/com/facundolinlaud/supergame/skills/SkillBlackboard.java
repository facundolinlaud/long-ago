package com.facundolinlaud.supergame.skills;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.managers.world.SkillsManager;
import com.facundolinlaud.supergame.managers.world.UIManager;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;

public class SkillBlackboard extends Blackboard {
    private SkillsManager skillsManager;

    public SkillBlackboard(Entity agent, LightsManager lightsManager, CameraManager cameraManager, UIManager uiManager,
                           AgentService agentService, CombatService combatService, ParticlesService particlesService,
                           ProjectilesService projectilesService, SkillsManager skillsManager) {
        super(agent, lightsManager, cameraManager, uiManager, agentService, combatService, particlesService,
                projectilesService);
        this.skillsManager = skillsManager;
    }

    @Override
    public SkillsManager getDomainTaskManager() {
        return skillsManager;
    }
}
