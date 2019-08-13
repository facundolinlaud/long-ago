package com.facundolinlaud.supergame.quests.dismantle;

public class NoQuestDismantle implements QuestDismantle {
    @Override
    public void dismantle() {
        System.out.println("Ending quest");
    }
}
