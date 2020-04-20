package com.facundolinlaud.supergame.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.List;
import java.util.Stack;

/**
 * Pops: nothing
 * Pushes: the stack's top n times
 */
public class RePushValueTask extends Task {
    private static final int DEFAULT_DEEPNESS = 1;

    private int deepness;
    private int times;

    public RePushValueTask(int deepness, int times) {
        this.deepness = deepness;
        this.times = times;
    }

    @Override
    public void activate() {
        Stack<Value> stack = getBlackboard().getStack();
        List<Value> copies = stack.subList(0, deepness);

        for(int i = 0; i < times; i++){
            copies.forEach(value -> stack.push(value));
        }

        completed();
    }
}
