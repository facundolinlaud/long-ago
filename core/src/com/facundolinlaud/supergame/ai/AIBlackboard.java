package com.facundolinlaud.supergame.ai;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.ai.pathfinding.MapGraph;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.managers.world.AIManager;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.managers.world.UIManager;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;

public class AIBlackboard extends Blackboard {
    private AIManager aiManager;

    public AIBlackboard(Entity agent, LightsManager lightsManager, CameraManager cameraManager, UIManager uiManager,
                        AgentService agentService, CombatService combatService, ParticlesService particlesService,
                        ProjectilesService projectilesService, AIManager aiManager) {
        super(agent, lightsManager, cameraManager, uiManager, agentService, combatService,
                particlesService, projectilesService);
        this.aiManager = aiManager;
    }

    @Override
    public AIManager getDomainTaskManager() {
        return aiManager;
    }
}
