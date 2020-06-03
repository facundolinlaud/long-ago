package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.quests.QuestBlackboard;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

public class QuestsManager extends PoolableTaskManager {
    public QuestsManager(Factories factories, AgentService agentService, DialogUIController dialogUIController, Engine engine) {
        Entity player = agentService.getPlayer();

        QuestBlackboard blackboard = new QuestBlackboard(player, dialogUIController, agentService, engine, this);

        factories.getQuestsFactory()
                .buildQuestChain(blackboard)
                .activate();
    }
}
