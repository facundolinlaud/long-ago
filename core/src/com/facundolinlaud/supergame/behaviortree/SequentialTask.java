package com.facundolinlaud.supergame.behaviortree;

import java.util.LinkedList;

public class SequentialTask<T extends Blackboard> extends CompositeTask<T> {
    public SequentialTask(LinkedList<Task> children) {
        super(children);
    }

    @Override
    public void activate() {
        children.removeFirst().activate();
    }

    @Override
    public void childCompleted(Task child) {
        if (children.isEmpty()) {
            completed();
        } else {
            activate();
        }
    }

    @Override
    public void childFailed(Task child) {
        failed();
    }
}
