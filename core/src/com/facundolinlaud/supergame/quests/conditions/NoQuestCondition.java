package com.facundolinlaud.supergame.quests.conditions;

public abstract class NoQuestCondition implements QuestCondition {
    @Override
    public void activate() {
        done();
    }

    @Override
    public abstract void done();
}
