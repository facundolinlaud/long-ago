package com.facundolinlaud.supergame.quests;

public abstract class PoolableTask extends Task {
    @Override
    public void activate() {
        getBlackboard().getQuestsManager().addPoolableTask(this);
    }

    public abstract void tick();

    @Override
    public void completed() {
        getBlackboard().getQuestsManager().removePoolableTask(this);
    }
}
