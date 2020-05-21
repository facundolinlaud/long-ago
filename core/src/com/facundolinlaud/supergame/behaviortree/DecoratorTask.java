package com.facundolinlaud.supergame.behaviortree;

import com.facundolinlaud.supergame.behaviortree.stack.Value;

import java.util.Stack;

public abstract class DecoratorTask<T extends Blackboard> extends BranchTask<T> {
    protected Task child;

    public DecoratorTask(Task child) {
        this.child = child;
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
}
