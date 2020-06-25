package com.facundolinlaud.supergame.ai.behavior;

import com.facundolinlaud.supergame.behaviortree.composites.SelectorTask;

import java.util.LinkedList;

public class BehaviorTask extends SelectorTask {
    public BehaviorTask(LinkedList children) {
        super(children);
    }

    @Override
    public void completed() {
        System.out.println("[BEHAVIOR] completed");
        stack.clear();
        reset();
        activate();
    }

    @Override
    public void failed() {
        System.out.println("[BEHAVIOR] failed");

        stack.clear();
        reset();
        activate();
    }
}
