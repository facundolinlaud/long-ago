package com.facundolinlaud.supergame.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.Task;

public class PopValueTask extends Task {
    @Override
    public void activate() {
        getBlackboard().getStack().pop();
        completed();
    }
}
