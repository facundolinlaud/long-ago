package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.factory.ItemFactory;
import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.quests.listeners.QuestObjetive;
import com.facundolinlaud.supergame.quests.listeners.QuestSlayObjective;
import com.facundolinlaud.supergame.quests.presentation.QuestAutoAcceptPresentation;
import com.facundolinlaud.supergame.quests.presentation.QuestPopUpPresentation;
import com.facundolinlaud.supergame.quests.rewards.QuestItemReward;
import com.facundolinlaud.supergame.quests.start.QuestEmptyStart;
import com.facundolinlaud.supergame.quests.wrapup.QuestEmptyEnd;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuestsManager {
    private Entity player;
    private Factories factories;

    public QuestsManager(Entity player, Factories factories) {
        this.player = player;
        this.factories = factories;

        Quest b = getB();
        Quest a = getA(b);

        a.present();
        a.start();
    }

    public Quest getB(){
        Quest b = new Quest();
        b.setPresentation(new QuestAutoAcceptPresentation(b));
        b.setStart(new QuestEmptyStart());
        List<QuestObjetive> objectives = new LinkedList();
        objectives.add(new QuestSlayObjective(b, "Kill id 1 amount 1!!", 1, 3));
        b.setObjectives(objectives);
        b.setQuestRewards(Arrays.asList(new QuestItemReward(player, getRewardsForB())));
        b.setEnd(new QuestEmptyEnd());
        b.setNextQuests(Arrays.asList());

        return b;
    }

    public Quest getA(Quest b){
        Quest a = new Quest();
        a.setPresentation(new QuestPopUpPresentation("Presenting Quest A"));
        a.setStart(new QuestEmptyStart());
        List<QuestObjetive> objectives = new LinkedList();
        objectives.add(new QuestSlayObjective(a, "Kill id 1 amount 3!!", 1, 1));
        objectives.add(new QuestSlayObjective(a, "Kill id 2 amount 2!!", 2, 1));
        a.setObjectives(objectives);
        a.setQuestRewards(Arrays.asList(new QuestItemReward(player, getRewardsForA())));
        a.setEnd(new QuestEmptyEnd());
        a.setNextQuests(Arrays.asList(b));

        return a;
    }

    private List<Entity> getRewardsForA(){
        ItemFactory factory = factories.getItemFactory();

        return Arrays.asList(
                factory.getItem(8).build(),
                factory.getItem(8).build()
        );
    }

    private List<Entity> getRewardsForB(){
        ItemFactory factory = factories.getItemFactory();

        return Arrays.asList(
                factory.getItem(8).build(),
                factory.getItem(5).build()
        );
    }
}
