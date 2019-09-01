package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.factory.AgentFactory;
import com.facundolinlaud.supergame.quests.PoolableTask;
import com.facundolinlaud.supergame.quests.Blackboard;
import com.facundolinlaud.supergame.quests.composites.ParallelTask;
import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.quests.composites.SequentialTask;
import com.facundolinlaud.supergame.quests.leafs.*;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class QuestsManager {
    private Set<PoolableTask> poolableTasks;

    public QuestsManager(Entity player, DialogUIController dialogUIController, AgentFactory agentFactory, Engine engine) {
        this.poolableTasks = new HashSet();

        Blackboard blackboard = new Blackboard(player, dialogUIController, agentFactory, engine, this);
        getA(blackboard).activate();
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

    public Quest getB(Blackboard blackboard){
        String title = "Fisherman";
        String firstDialog = "Now I need you to kill an elf.{WAIT} Will you?";
        InputDialogTask dialogTask1 = new InputDialogTask(title, firstDialog);
        SpawnTask spawnTask = new SpawnTask(4, new Vector2(22, 30));
        SlayTask slayTask = new SlayTask(4, 1);
        GoldRewardTask goldRewardTask = new GoldRewardTask(5);
        String secondDialog = "Thank you very much!{WAIT} Here's the gold I promised...";
        TextDialogTask dialogTask2 = new TextDialogTask(title, secondDialog);

        return new Quest(Arrays.asList(), blackboard, dialogTask1, spawnTask, slayTask, goldRewardTask, dialogTask2);
    }

    public Quest getA(Blackboard blackboard){
        String title = "Fisherman";
        String firstDialog = "Hey...{WAIT} You!{WAIT} I need your help with something...{WAIT} " +
                "I'll pay you good coin for your sword.{WAIT} What do you say?{WAIT} Want to help an old man?";
        String secondDialog = "I need you to slay one Orc and two Skeletons.{WAIT}" +
                "They are harrasing my sheeeeeeepsssss!!{WAIT} Will you help me?";
        String thirdDialog = "Thank you for your help!{WAIT} Here's what I promised!";

        SpawnTask spawnTask1 = new SpawnTask(3, new Vector2(30, 35));
        InteractionTask talkToTask1 = new InteractionTask(3);
        TextDialogTask dialogTask1 = new TextDialogTask(title, firstDialog);
        TextDialogTask dialogTask2 = new TextDialogTask(title, "So?");
        InputDialogTask dialogTask3 = new InputDialogTask(title, secondDialog);
        SequentialTask sequentialTask1 = new SequentialTask(spawnTask1, talkToTask1, dialogTask1, dialogTask2, dialogTask3);

        SlayTask slayTask1 = new SlayTask(1, 1);
        SlayTask slayTask2 = new SlayTask(2, 2);
        ParallelTask parallelTask1 = new ParallelTask(slayTask1, slayTask2);

        TextDialogTask dialogTask4 = new TextDialogTask(title, thirdDialog);
        GoldRewardTask goldRewardTask = new GoldRewardTask(10);

        return new Quest(Arrays.asList(getB(blackboard)), blackboard, sequentialTask1,
                parallelTask1, dialogTask4, goldRewardTask);
    }
}
