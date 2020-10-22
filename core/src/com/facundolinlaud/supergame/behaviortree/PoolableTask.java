package com.facundolinlaud.supergame.behaviortree;

import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;

public abstract class PoolableTask<T extends Blackboard> extends LeafTask<T> {
    @Override
    public void activate() {
        getBlackboard().getDomainTaskManager().addPoolableTask(this);
    }

    public abstract void tick(float delta);

    @Override
    public void completed() {
        getBlackboard().getDomainTaskManager().removePoolableTask(this);
        super.completed();
    }
}
