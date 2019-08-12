package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.factory.ItemFactory;
import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.quests.listeners.QuestObjective;
import com.facundolinlaud.supergame.quests.listeners.QuestSlayObjective;
import com.facundolinlaud.supergame.quests.presentation.QuestAutoAcceptPresentation;
import com.facundolinlaud.supergame.quests.presentation.QuestDialogPresentation;
import com.facundolinlaud.supergame.quests.rewards.QuestGoldReward;
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

        Quest c = getC();
        Quest b = getB(c);
        Quest a = getA(b);

        a.present();
        a.start();
    }

    public Quest getC(){
        Quest c = new Quest();
        c.setPresentation(new QuestAutoAcceptPresentation(c));
        c.setStart(new QuestEmptyStart());
        List<QuestObjective> objectives = new LinkedList();
        objectives.add(new QuestSlayObjective(c, "Slay two skeletons", 1, 2));
        c.setObjectives(objectives);
        c.setQuestRewards(Arrays.asList(new QuestGoldReward(player, 60)));
        c.setEnd(new QuestEmptyEnd());
        c.setNextQuests(Arrays.asList());

        return c;
    }

    public Quest getB(Quest c){
        Quest b = new Quest();
        b.setPresentation(new QuestAutoAcceptPresentation(b));
        b.setStart(new QuestEmptyStart());
        List<QuestObjective> objectives = new LinkedList();
        objectives.add(new QuestSlayObjective(b, "Slay three skeletons", 1, 3));
        b.setObjectives(objectives);
        b.setQuestRewards(Arrays.asList(new QuestGoldReward(player, 60)));
        b.setEnd(new QuestEmptyEnd());
        b.setNextQuests(Arrays.asList(c));

        return b;
    }

    public Quest getA(Quest b){
        Quest a = new Quest();
        a.setPresentation(new QuestDialogPresentation("Skeleton Slayer"));
        a.setStart(new QuestEmptyStart());
        List<QuestObjective> objectives = new LinkedList();
        objectives.add(new QuestSlayObjective(a, "Slay one skeleton", 1, 1));
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
}
