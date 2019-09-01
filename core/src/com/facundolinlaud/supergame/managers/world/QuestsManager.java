package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.factory.AgentFactory;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.quests.Blackboard;
import com.facundolinlaud.supergame.quests.PoolableTask;
import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.quests.composites.ParallelTask;
import com.facundolinlaud.supergame.quests.composites.SequentialTask;
import com.facundolinlaud.supergame.quests.leafs.*;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

import java.util.HashSet;
import java.util.Set;

public class QuestsManager {
    private Set<PoolableTask> poolableTasks;

    public QuestsManager(Factories factories, Entity player, DialogUIController dialogUIController, Engine engine) {
        this.poolableTasks = new HashSet();

        Blackboard blackboard = new Blackboard(player, dialogUIController,
                factories.getAgentFactory(), engine, this);

        factories.getQuestsFactory()
                .buildQuestChain(blackboard)
                .activate();
    }

    public void addPoolableTask(PoolableTask poolableTask){
        poolableTasks.add(poolableTask);
    }

    public void removePoolableTask(PoolableTask poolableTask){
        poolableTasks.remove(poolableTask);
    }

    public void tick(){
        poolableTasks.forEach(poolableTask -> poolableTask.tick());
    }
}
