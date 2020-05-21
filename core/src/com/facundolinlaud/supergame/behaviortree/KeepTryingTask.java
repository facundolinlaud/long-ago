package com.facundolinlaud.supergame.behaviortree;

public class KeepTryingTask<T extends Blackboard> extends DecoratorTask<T> {
    public KeepTryingTask(Task child) {
        super(child);
        child.setParent(this);
    }

    @Override
    public void activate() {
        child.activate();
    }

    @Override
    void childCompleted(Task child) {
        completed();
    }

    @Override
    void childFailed(Task child) {
        child.reset();
        activate();
    }
}
