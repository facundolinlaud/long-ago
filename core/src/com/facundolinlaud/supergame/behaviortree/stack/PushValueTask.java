package com.facundolinlaud.supergame.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.LeafTask;

public class PushValueTask extends LeafTask {
    private Value value;

    public PushValueTask(Value value) {
        this.value = value;
    }

    @Override
    public void activate() {
        stack.push(value);
        completed();
    }
}
