package com.facundolinlaud.supergame.behaviortree.composites;

import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.LinkedList;

public class SequentialTask<T extends Blackboard> extends CompositeTask<T> {
    public SequentialTask(LinkedList<Task> children) {
        super(children);
    }

    @Override
    public void activate() {
        childrenIterator.next().activate();
    }

    @Override
    public void childCompleted(Task child) {
        if (childrenIterator.hasNext()) {
            activate();
        } else {
            completed();
        }
    }

    @Override
    public void childFailed(Task child) {
        failed();
    }
}
