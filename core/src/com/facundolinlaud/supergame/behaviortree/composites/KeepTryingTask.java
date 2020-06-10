package com.facundolinlaud.supergame.behaviortree.composites;

import com.facundolinlaud.supergame.behaviortree.Task;

public class KeepTryingTask<T extends Blackboard> extends DecoratorTask<T> {
    public KeepTryingTask(Task child) {
        super(child);
    }

    @Override
    public void activate() {
        child.activate();
    }

    @Override
    public void childCompleted(Task child) {
        completed();
    }

    @Override
    public void childFailed(Task child) {
        child.reset();
        activate();
    }
}
