package com.facundolinlaud.supergame.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;

public class MultiplyValuesTask<T extends Blackboard> extends LeafTask<T> {
    @Override
    public void activate() {
        Value a = stack.pop();
        Value b = stack.pop();

        Float result = a.getFloat() * b.getFloat();

        stack.add(new Value(result));
        completed();
    }
}
