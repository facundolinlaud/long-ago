package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.factory.ItemFactory;
import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.quests.dismantle.DialogQuestDismantle;
import com.facundolinlaud.supergame.quests.dismantle.NoQuestDismantleCondition;
import com.facundolinlaud.supergame.quests.dismantle.QuestDismantle;
import com.facundolinlaud.supergame.quests.listeners.QuestObjective;
import com.facundolinlaud.supergame.quests.listeners.QuestSlayObjective;
import com.facundolinlaud.supergame.quests.presentation.NoQuestPresentationCondition;
import com.facundolinlaud.supergame.quests.presentation.QuestDialogPresentation;
import com.facundolinlaud.supergame.quests.rewards.QuestItemReward;
import com.facundolinlaud.supergame.quests.start.NoQuestSetup;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuestsManager {
    private Entity player;
    private Factories factories;

    public QuestsManager(Entity player, Factories factories, DialogUIController dialogUIController) {
        this.player = player;
        this.factories = factories;

//        Quest c = getC();
//        Quest b = getB(c);
        Quest a = getA(dialogUIController);
        a.activate();
    }

    public Quest getA(DialogUIController uiController){
        Quest a = new Quest();
        a.setPresentationsCondition(new NoQuestPresentationCondition(a));
        a.setPresentations(Arrays.asList(new QuestDialogPresentation(uiController, a),
                new QuestDialogPresentation(uiController, a),
                new QuestDialogPresentation(uiController, a)));
        a.setSetup(new NoQuestSetup());
        List<QuestObjective> objectives = new LinkedList();
        objectives.add(new QuestSlayObjective(a, "Slay one skeleton", 1, 1));
        a.setObjectives(objectives);
        a.setDismantleCondition(new NoQuestDismantleCondition(a));
        LinkedList<QuestDismantle> dismantles = new LinkedList();
        dismantles.add(new DialogQuestDismantle(uiController, a));
        dismantles.add(new DialogQuestDismantle(uiController, a));
        a.setDismantles(dismantles);
        a.setRewards(Arrays.asList(new QuestItemReward(player, getRewardsForA())));
        a.setNextQuests(Arrays.asList());

        return a;
    }

    private List<Entity> getRewardsForA(){
        ItemFactory factory = factories.getItemFactory();

        return Arrays.asList(
            factory.getItem(8).build(),
            factory.getItem(8).build()
        );
    }
}
