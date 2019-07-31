package com.facundolinlaud.supergame.quests;

import com.facundolinlaud.supergame.quests.listeners.QuestObjetive;
import com.facundolinlaud.supergame.quests.presentation.QuestPresentation;
import com.facundolinlaud.supergame.quests.rewards.QuestReward;
import com.facundolinlaud.supergame.quests.start.QuestStart;
import com.facundolinlaud.supergame.quests.wrapup.QuestEnd;

import java.util.List;

public class Quest {
    private QuestPresentation presentation;
    private QuestStart start;
    private List<QuestObjetive> objectives;
    private QuestEnd end;
    private List<QuestReward> questRewards;
    private List<Quest> nextQuests;

    public Quest() {}

    public Quest(QuestPresentation presentation, QuestStart start, List<QuestObjetive> objectives,
                 QuestEnd end, List<QuestReward> questRewards, List<Quest> nextQuests) {
        this.presentation = presentation;
        this.start = start;
        this.objectives = objectives;
        this.end = end;
        this.questRewards = questRewards;
        this.nextQuests = nextQuests;
    }

    public void onObjectiveCompleted(QuestObjetive objective) {
        objectives.remove(objective);

        if(objectives.isEmpty()){
            System.out.println("Objectives depleted");
            end.end();
            questRewards.forEach(r -> r.reward());
            presentNextQuests();
        }
    }

    public void present(){
        presentation.present();
    }

    public void start(){
        System.out.println("Starting quest");
        start.start();
        objectives.forEach(o -> o.activate());
    }

    private void presentNextQuests() {
        System.out.println("Presenting next quests");
        nextQuests.forEach(q -> q.present());
    }

    /* Setters so it's easier to instantiate */
    public void setPresentation(QuestPresentation presentation) {
        this.presentation = presentation;
    }

    public void setStart(QuestStart start) {
        this.start = start;
    }

    public void setObjectives(List<QuestObjetive> objectives) {
        this.objectives = objectives;
    }

    public void setEnd(QuestEnd end) {
        this.end = end;
    }

    public void setQuestRewards(List<QuestReward> questRewards) {
        this.questRewards = questRewards;
    }

    public void setNextQuests(List<Quest> nextQuests) {
        this.nextQuests = nextQuests;
    }
}
