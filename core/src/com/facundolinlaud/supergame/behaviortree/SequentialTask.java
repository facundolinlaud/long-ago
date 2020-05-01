package com.facundolinlaud.supergame.behaviortree;

import java.util.LinkedList;

public class SequentialTask<T extends Blackboard> extends CompositeTask<T> {
    public SequentialTask(LinkedList<Task> children) {
        super(children);
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
    public void activate() {
        Task child = children.removeFirst();

        completed.add(child);
        child.activate();
    }
}
