package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.quests.QuestBlackboard;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;

public class QuestsManager extends PoolableTaskManager {
    public QuestsManager(Factories factories, LightsManager lightsManager, CameraManager cameraManager,
                         UIManager uiManager, AgentService agentService, CombatService combatService,
                         ParticlesService particlesService, ProjectilesService projectilesService) {
        Entity player = agentService.getPlayer();

        QuestBlackboard blackboard = new QuestBlackboard(player, lightsManager, cameraManager, uiManager, agentService,
                combatService, particlesService, projectilesService, this);

        factories.getQuestsFactory()
                .buildQuestChain(blackboard)
                .activate();
    }
}
