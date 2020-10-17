package com.facundolinlaud.supergame.ai.behavior;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.composites.SelectorTask;
import com.facundolinlaud.supergame.behaviortree.composites.SequentialTask;

import java.util.LinkedList;
import java.util.Stack;

public class BehaviorTask extends SelectorTask<BehaviorBlackboard> {
    public BehaviorTask(LinkedList children) {
        super(children);
        setStack(new Stack());
    }

    @Override
    public void completed() {
        stack.clear();
        reset();
        onBehaviorFinished();
    }

    @Override
    public void failed() {
        stack.clear();
        reset();
        onBehaviorFinished();
    }

    private void onBehaviorFinished() {
        Entity agent = getBlackboard().getAgent();
        getBlackboard().getDomainTaskManager().onBehaviorFinished(agent);
    }
}
