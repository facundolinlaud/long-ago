package com.facundolinlaud.supergame.behaviortree.composites;

import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.LinkedList;

public class SelectorTask<T extends Blackboard> extends SequentialTask<T> {
    public SelectorTask(LinkedList<Task> children) {
        super(children);
    }

    @Override
    public void childFailed(Task child) {
        if (!childrenIterator.hasNext()) {
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
