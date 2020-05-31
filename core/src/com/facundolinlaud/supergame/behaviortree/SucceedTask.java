package com.facundolinlaud.supergame.behaviortree;

public class SucceedTask extends DecoratorTask {
    public SucceedTask(Task child) {
        super(child);
    }

    @Override
    void childCompleted(Task child) {
        completed();
    }

    @Override
    void childFailed(Task child) {
        completed();
    }

    @Override
    public void activate() {
        child.activate();
    }
}
