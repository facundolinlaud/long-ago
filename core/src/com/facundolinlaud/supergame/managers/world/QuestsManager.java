package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.PoolableTask;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.quests.QuestBlackboard;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

import java.util.HashSet;
import java.util.Set;

public class QuestsManager extends PoolableTaskManager {
    public QuestsManager(Factories factories, Entity player, DialogUIController dialogUIController, Engine engine) {
        QuestBlackboard blackboard = new QuestBlackboard(player, dialogUIController,
                factories.getAgentFactory(), engine, this);

        factories.getQuestsFactory()
                .buildQuestChain(blackboard)
                .activate();
    }
}
