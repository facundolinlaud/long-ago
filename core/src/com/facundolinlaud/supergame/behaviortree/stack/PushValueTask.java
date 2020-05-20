package com.facundolinlaud.supergame.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.Task;

public class PushValueTask extends Task {
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
