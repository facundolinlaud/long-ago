package com.facundolinlaud.supergame.quests;

import com.facundolinlaud.supergame.quests.listeners.QuestObjective;
import com.facundolinlaud.supergame.quests.presentation.QuestPresentation;
import com.facundolinlaud.supergame.quests.rewards.QuestReward;
import com.facundolinlaud.supergame.quests.start.QuestStart;
import com.facundolinlaud.supergame.quests.wrapup.QuestEnd;

import java.util.List;

public class Quest {
    private List<QuestPresentation> presentation;
    private QuestStart start;
    private List<QuestObjective> objectives;
    private QuestEnd end;
    private List<QuestReward> questRewards;
    private List<Quest> nextQuests;

    public Quest() {}

    public void onObjectiveCompleted(QuestObjective objective) {
        objectives.remove(objective);

        if(objectives.isEmpty()){
            end.end();
            questRewards.forEach(r -> r.reward());
            presentNextQuests();
        }
    }

    public void present(){
        presentation.present();
    }

    public void start(){
        System.out.println("Starting quest!");
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

    public void setObjectives(List<QuestObjective> objectives) {
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
