package com.facundolinlaud.supergame.behaviortree.leaves;

import com.facundolinlaud.supergame.behaviortree.PoolableTask;

public class WaitTask extends PoolableTask {
    private float remainingTime;

    public WaitTask(float time) {
        this.remainingTime = time;
    }

    @Override
    public void tick(float delta) {
        if (remainingTime <= 0)
            super.completed();
        else this.remainingTime -= delta;
    }
}
