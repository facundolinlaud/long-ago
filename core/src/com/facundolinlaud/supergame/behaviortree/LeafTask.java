package com.facundolinlaud.supergame.behaviortree;

import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;

import java.util.Stack;

public abstract class LeafTask<T extends Blackboard> extends Task<T> {
    @Override
    protected void onBlackboardAvailable(T blackboard) {
    }

    @Override
    protected void onStackAvailable(Stack stack) {
    }

    @Override
    public void reset() {
    }

    @Override
    protected void postAbort() {
    }
}
