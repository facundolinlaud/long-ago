package com.facundolinlaud.supergame.behaviortree;

import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.behaviortree.composites.BranchTask;
import com.facundolinlaud.supergame.behaviortree.stack.Value;

import java.util.Stack;

public abstract class Task<T extends Blackboard> {
    protected BranchTask parent;
    protected Stack<Value> stack;
    protected boolean aborted;
    private T blackboard;

    abstract public void activate();

    public void completed() {
        if (!aborted) {
            parent.childCompleted(this);
        }
    }

    public void failed() {
        if (!aborted) {
            parent.childFailed(this);
        }
    }

    public void setParent(BranchTask parent) {
        this.parent = parent;
    }

    public void setStack(Stack<Value> stack) {
        this.stack = stack;
        onStackAvailable(stack);
    }

    public void setBlackboard(T blackboard) {
        this.blackboard = blackboard;
        onBlackboardAvailable(blackboard);
    }

    public T getBlackboard() {
        return this.blackboard;
    }

    protected abstract void onBlackboardAvailable(T blackboard);

    protected abstract void onStackAvailable(Stack<Value> stack);

    public abstract void reset();

    public void abort() {
        aborted = true;
        postAbort();
    }

    protected abstract void postAbort();
}
