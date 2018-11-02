package com.facundolinlaud.supergame.ai.decisionmaking;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

public class PatrolTask extends LeafTask<Blackboard> {
    @Override
    public Status execute() {
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new PatrolTask();
    }
}
