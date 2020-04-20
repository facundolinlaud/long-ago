package com.facundolinlaud.supergame.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.Stack;

public class SumValuesTask extends Task {
    @Override
    public void activate() {
        Stack<Value> stack = getBlackboard().getStack();

        Value a = stack.pop();
        Value b = stack.pop();

        Float result = a.getFloatValue() + b.getFloatValue();

        stack.add(new Value(result));
        completed();
    }
}
