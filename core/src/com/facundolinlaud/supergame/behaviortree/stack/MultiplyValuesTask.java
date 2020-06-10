package com.facundolinlaud.supergame.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.behaviortree.Task;

public class MultiplyValuesTask<T extends Blackboard> extends Task<T> {
    @Override
    public void activate() {
        Value a = stack.pop();
        Value b = stack.pop();

        Float result = a.getFloat() * b.getFloat();

        stack.add(new Value(result));
        completed();
    }
}
