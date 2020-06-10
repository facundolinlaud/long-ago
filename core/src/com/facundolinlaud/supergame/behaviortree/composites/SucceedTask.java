package com.facundolinlaud.supergame.behaviortree.composites;

import com.facundolinlaud.supergame.behaviortree.Task;

public class SucceedTask extends DecoratorTask {
    public SucceedTask(Task child) {
        super(child);
    }

    @Override
    public void childCompleted(Task child) {
        completed();
    }

    @Override
    public void childFailed(Task child) {
        completed();
    }

    @Override
    public void activate() {
        child.activate();
    }
}
