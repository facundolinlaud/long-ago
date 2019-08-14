package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.quests.*;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

import java.util.Arrays;
import java.util.LinkedList;

public class QuestsManager {
    public QuestsManager(Entity player, DialogUIController dialogUIController) {
        Blackboard blackboard = new Blackboard(player, dialogUIController);
        Quest quest = getNewQuest(blackboard);
        quest.activate();
    }

    public Quest getNewQuest(Blackboard blackboard){
        String title = "Some dude";
        String firstDialog = "Hey there...{WAIT} I'm looking for some help...{WAIT} Do you want to help me?{WAIT}" +
                "I will pay you 5 coins for it.{WAIT} What do you say?";
        String secondDialog = "I need you to slay one Orc and two Skeletons.{WAIT}" +
                "They are harrasing my sheeeeeeepsssss!!{WAIT} Will you help me?";
        String thirdDialog = "Thank you for your help!{WAIT} Here's what I promised!";

        TextDialogTask dialogTask1 = new TextDialogTask(title, firstDialog, blackboard);
        InputDialogTask dialogTask2 = new InputDialogTask(title, secondDialog, blackboard);
        LinkedList<Task> dialogs = new LinkedList();
        dialogs.add(dialogTask1);
        dialogs.add(dialogTask2);

        SequentialTask sequentialTask1 = new SequentialTask(dialogs);

        SlayTask slayTask1 = new SlayTask(1, 1);
        SlayTask slayTask2 = new SlayTask(2, 2);
        LinkedList<Task> slays = new LinkedList();
        slays.add(slayTask1);
        slays.add(slayTask2);

        ParallelTask parallelTask1 = new ParallelTask(slays);

        TextDialogTask dialogTask3 = new TextDialogTask(title, thirdDialog, blackboard);
        GoldRewardTask goldRewardTask = new GoldRewardTask(blackboard, 5);
        LinkedList<Task> composites = new LinkedList();
        composites.add(sequentialTask1);
        composites.add(parallelTask1);
        composites.add(dialogTask3);
        composites.add(goldRewardTask);

        return new Quest(composites, Arrays.asList());
    }
}
