package com.facundolinlaud.supergame.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.LeafTask;

public class PopValueTask extends LeafTask {
    @Override
    public void activate() {
        stack.pop();
        completed();
    }
}
