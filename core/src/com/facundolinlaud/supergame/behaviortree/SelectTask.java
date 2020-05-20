package com.facundolinlaud.supergame.behaviortree;

import java.util.LinkedList;

public class SelectTask<T extends Blackboard> extends SequentialTask<T> {
    public SelectTask(LinkedList<Task> children) {
        super(children);
    }

    @Override
    public void childFailed(Task child) {
        if (children.isEmpty()) {
            failed();
            return;
        }

        activate();
    }

    @Override
    public void childCompleted(Task child) {
        completed();
    }
}
