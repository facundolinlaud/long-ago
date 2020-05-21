package com.facundolinlaud.supergame.behaviortree;

import com.facundolinlaud.supergame.behaviortree.stack.Value;

import java.util.LinkedList;
import java.util.Stack;

public class ParallelTask<T extends Blackboard> extends CompositeTask<T> {
    private int completedChildren;

    public ParallelTask(LinkedList<Task> children) {
        super(children);

        this.stack = new Stack();
        this.completedChildren = 0;
    }

    @Override
    public void activate() {
        Stack<Value> clone = (Stack<Value>) stack.clone();

        children.forEach(task -> {
            task.setStack(clone);
            task.activate();
        });
    }

    @Override
    public void childCompleted(Task child) {
        completedChildren ++;

        if(completedChildren == children.size())
            completed();
    }

    @Override
    void childFailed(Task child) {
        failed();
    }

    @Override
    protected void onStackAvailable(Stack<Value> stack) {}

    @Override
    public void reset() {
        completedChildren = 0;
    }
}
