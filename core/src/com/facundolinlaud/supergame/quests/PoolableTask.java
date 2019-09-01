package com.facundolinlaud.supergame.quests;

public abstract class PoolableTask extends Task {
    @Override
    public void activate() {}

    public abstract void tick();
}
