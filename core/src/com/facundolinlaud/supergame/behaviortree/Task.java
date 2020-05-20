package com.facundolinlaud.supergame.behaviortree;

import com.facundolinlaud.supergame.behaviortree.stack.Value;

import java.util.Stack;

public abstract class Task<T extends Blackboard> {
    protected CompositeTask parent;
    protected Stack<Value> stack;
    private T blackboard;

    public abstract void activate();

    public void completed() {
        parent.childCompleted(this);
    }

    public void failed(){
        parent.childFailed(this);
    }

    public void setParent(CompositeTask parent) {
        this.parent = parent;
    }

    public void setStack(Stack<Value> stack) {
        this.stack = stack;
        onStackAvailable(stack);
    }

    public void setBlackboard(T blackboard){
        this.blackboard = blackboard;
        onBlackboardAvailable(blackboard);
    }

    public T getBlackboard(){
        return this.blackboard;
    }

    protected void onBlackboardAvailable(T blackboard){}

    protected void onStackAvailable(Stack<Value> stack){}
}
