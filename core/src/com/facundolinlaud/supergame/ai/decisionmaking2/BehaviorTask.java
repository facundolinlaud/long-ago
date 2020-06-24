package com.facundolinlaud.supergame.ai.decisionmaking2;

import com.facundolinlaud.supergame.behaviortree.composites.SelectorTask;

import java.util.LinkedList;

public class BehaviorTask extends SelectorTask {
    public BehaviorTask(LinkedList children) {
        super(children);
    }

    @Override
    public void completed() {
        reset();
    }

    @Override
    public void failed() {
        reset();
    }
}
