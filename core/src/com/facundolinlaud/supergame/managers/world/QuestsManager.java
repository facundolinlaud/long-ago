package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.factory.AgentFactory;
import com.facundolinlaud.supergame.quests.QuestBlackboard;
import com.facundolinlaud.supergame.quests.Task;
import com.facundolinlaud.supergame.quests.composites.ParallelTask;
import com.facundolinlaud.supergame.quests.composites.Quest;
import com.facundolinlaud.supergame.quests.composites.SequentialTask;
import com.facundolinlaud.supergame.quests.leafs.*;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

import java.util.Arrays;
import java.util.LinkedList;

public class QuestsManager {
    public QuestsManager(Entity player, DialogUIController dialogUIController,
                         AgentFactory agentFactory, Engine engine) {
        QuestBlackboard blackboard = new QuestBlackboard(player, dialogUIController, agentFactory, engine);
        Quest quest = getA(blackboard);
        quest.activate();
    }

    public Quest getB(QuestBlackboard blackboard){
        String title = "Fisherman";
        String firstDialog = "Now I need you to kill an elf.{WAIT} Will you?";
        InputDialogTask dialogTask1 = new InputDialogTask(title, firstDialog);
        SpawnTask spawnTask = new SpawnTask(4, new Vector2(22, 30));
        SlayTask slayTask = new SlayTask(4, 1);
        GoldRewardTask goldRewardTask = new GoldRewardTask(5);
        String secondDialog = "Thank you very much!{WAIT} Here's the gold I promised...";
        TextDialogTask dialogTask2 = new TextDialogTask(title, secondDialog);

        LinkedList<Task> composites = new LinkedList();
        composites.add(dialogTask1);
        composites.add(spawnTask);
        composites.add(slayTask);
        composites.add(goldRewardTask);
        composites.add(dialogTask2);

        return new Quest(composites, Arrays.asList(), blackboard);
    }

    public Quest getA(QuestBlackboard blackboard){
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
        LinkedList<Task> dialogs = new LinkedList();
        dialogs.add(spawnTask1);
        dialogs.add(talkToTask1);
        dialogs.add(dialogTask1);
        dialogs.add(dialogTask2);
        dialogs.add(dialogTask3);

        SequentialTask sequentialTask1 = new SequentialTask(dialogs);

        SlayTask slayTask1 = new SlayTask(1, 1);
        SlayTask slayTask2 = new SlayTask(2, 2);

        LinkedList<Task> slays = new LinkedList();
        slays.add(slayTask1);
        slays.add(slayTask2);

        ParallelTask parallelTask1 = new ParallelTask(slays);

        TextDialogTask dialogTask4 = new TextDialogTask(title, thirdDialog);
        GoldRewardTask goldRewardTask = new GoldRewardTask(10);
        LinkedList<Task> composites = new LinkedList();
        composites.add(sequentialTask1);
        composites.add(parallelTask1);
        composites.add(dialogTask4);
        composites.add(goldRewardTask);

        return new Quest(composites, Arrays.asList(getB(blackboard)), blackboard);
    }
}
