package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.PoolableTask;
import com.facundolinlaud.supergame.behaviortree.TaskPoolableManager;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.quests.QuestBlackboard;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

import java.util.HashSet;
import java.util.Set;

public class QuestsManager implements TaskPoolableManager {
    private Set<PoolableTask> poolableTasks;

    public QuestsManager(Factories factories, Entity player, DialogUIController dialogUIController, Engine engine) {
        this.poolableTasks = new HashSet();

        QuestBlackboard blackboard = new QuestBlackboard(player, dialogUIController,
                factories.getAgentFactory(), engine, this);

        factories.getQuestsFactory()
                .buildQuestChain(blackboard)
                .activate();
    }

    @Override
    public void addPoolableTask(PoolableTask poolableTask){
        poolableTasks.add(poolableTask);
    }

    @Override
    public void removePoolableTask(PoolableTask poolableTask){
        poolableTasks.remove(poolableTask);
    }

    public void tick(float delta){
        poolableTasks.forEach(poolableTask -> poolableTask.tick(delta));
    }
}
