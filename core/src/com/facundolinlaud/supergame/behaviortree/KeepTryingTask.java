package com.facundolinlaud.supergame.behaviortree;

public class KeepTryingTask<T extends Blackboard> extends DecoratorTask<T> {
    public KeepTryingTask(Task child) {
        super(child);
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
