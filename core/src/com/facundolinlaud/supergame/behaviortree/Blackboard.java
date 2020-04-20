package com.facundolinlaud.supergame.behaviortree;

import com.facundolinlaud.supergame.behaviortree.stack.Value;

import java.util.Stack;

public abstract class Blackboard {
    private Stack<Value> stack;

    public Blackboard() {
        this.stack = new Stack();
    }

    public Stack<Value> getStack(){
        return this.stack;
    }

    public abstract PoolableTaskManager getDomainManager();
}
