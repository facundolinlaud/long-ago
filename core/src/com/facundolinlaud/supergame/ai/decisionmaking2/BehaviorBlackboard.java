package com.facundolinlaud.supergame.ai.decisionmaking2;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.managers.world.*;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;

public class BehaviorBlackboard extends Blackboard {
    private BehaviorManager behaviorManager;

    public BehaviorBlackboard(Entity agent, LightsManager lightsManager, CameraManager cameraManager,
                              SkillsManager skillsManager, UIManager uiManager, AgentService agentService,
                              CombatService combatService, ParticlesService particlesService,
                              ProjectilesService projectilesService, BehaviorManager behaviorManager) {
        super(agent, lightsManager, cameraManager, skillsManager, uiManager, agentService, combatService,
                particlesService, projectilesService);
        this.behaviorManager = behaviorManager;
    }

    @Override
    public BehaviorManager getDomainTaskManager() {
        return behaviorManager;
    }
}
