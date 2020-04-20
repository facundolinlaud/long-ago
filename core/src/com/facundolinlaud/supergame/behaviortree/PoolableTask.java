package com.facundolinlaud.supergame.behaviortree;

public abstract class PoolableTask<T extends Blackboard> extends Task<T> {
    @Override
    public void activate() {
        getBlackboard().getDomainManager().addPoolableTask(this);
    }

    public abstract void tick(float delta);

    @Override
    public void completed() {
        getBlackboard().getDomainManager().removePoolableTask(this);
        super.completed();
    }
}
