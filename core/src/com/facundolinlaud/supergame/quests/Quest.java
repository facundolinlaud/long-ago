package com.facundolinlaud.supergame.quests;

import com.facundolinlaud.supergame.quests.conditions.QuestCondition;
import com.facundolinlaud.supergame.quests.dismantle.QuestDismantle;
import com.facundolinlaud.supergame.quests.listeners.QuestObjective;
import com.facundolinlaud.supergame.quests.presentation.QuestPresentation;
import com.facundolinlaud.supergame.quests.rewards.QuestReward;
import com.facundolinlaud.supergame.quests.start.QuestSetup;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Quest {
    private QuestCondition presentationsCondition;
    private List<QuestPresentation> presentations;
    private QuestSetup setup;
    private List<QuestObjective> objectives;
    private QuestCondition dismantleCondition;
    private LinkedList<QuestDismantle> dismantles;
    private List<QuestReward> rewards;
    private List<Quest> nextQuests;

    private Iterator<QuestPresentation> presentationIt;

    public void resetPresentations() {
        this.presentationIt = presentations.iterator();
    }

    public void activate(){
        presentationsCondition.activate();
    }

    public void nextPresentation(){
        if(presentationIt.hasNext()){
            presentationIt.next().present();
        }else{
            setup();
        }
    }

    public void setup(){
        System.out.println("Starting quest!");
        setup.setup();
        objectives.forEach(o -> o.activate());
    }

    public void onObjectiveCompleted(QuestObjective objective) {
        objectives.remove(objective);

        if(objectives.isEmpty())
            dismantleCondition.activate();
    }

    public void nextDismantle(){
        if(!dismantles.isEmpty()) {
            dismantles.removeFirst().dismantle();
        }else{
            rewards.forEach(r -> r.reward());
            activateNextQuests();
        }
    }

    private void activateNextQuests() {
        System.out.println("Presenting next quests");
        nextQuests.forEach(q -> q.activate());
    }

    /* Setters so it's easier to instantiate */
    public void setPresentationsCondition(QuestCondition presentationsCondition) {
        this.presentationsCondition = presentationsCondition;
    }

    public void setPresentations(List<QuestPresentation> presentations) {
        this.presentations = presentations;
        resetPresentations();
    }

    public void setSetup(QuestSetup setup) {
        this.setup = setup;
    }

    public void setObjectives(List<QuestObjective> objectives) {
        this.objectives = objectives;
    }

    public void setDismantleCondition(QuestCondition dismantleCondition) {
        this.dismantleCondition = dismantleCondition;
    }

    public void setDismantles(LinkedList<QuestDismantle> dismantles) {
        this.dismantles = dismantles;
    }

    public void setRewards(List<QuestReward> rewards) {
        this.rewards = rewards;
    }

    public void setNextQuests(List<Quest> nextQuests) {
        this.nextQuests = nextQuests;
    }
}
