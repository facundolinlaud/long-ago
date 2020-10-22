package com.facundolinlaud.supergame.quests;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.managers.world.*;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;

public class QuestBlackboard extends Blackboard {
    private QuestsManager questsManager;

    public QuestBlackboard(Entity agent, LightsManager lightsManager, CameraManager cameraManager,
                           SkillsManager skillsManager, UIManager uiManager, AgentService agentService,
                           CombatService combatService, ParticlesService particlesService,
                           ProjectilesService projectilesService, QuestsManager questsManager) {
        super(agent, lightsManager, cameraManager, skillsManager, uiManager, agentService, combatService,
                particlesService, projectilesService);
        this.questsManager = questsManager;
    }

    @Override
    public PoolableTaskManager getDomainTaskManager() {
        return questsManager;
    }
}
