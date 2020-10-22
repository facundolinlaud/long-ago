package com.facundolinlaud.supergame.behaviortree.composites;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.stack.Value;

import java.util.Stack;

public abstract class DecoratorTask<T extends Blackboard> extends BranchTask<T> {
    protected Task child;

    public DecoratorTask(Task child) {
        this.child = child;
        child.setParent(this);
    }

    @Override
    protected void onBlackboardAvailable(T blackboard) {
        child.setBlackboard(blackboard);
    }

    @Override
    protected void onStackAvailable(Stack<Value> stack) {
        child.setStack(stack);
    }

    @Override
    public void reset() {
        child.reset();
    }

    @Override
    protected void postAbort() {
        child.abort();
    }
}
