package com.facundolinlaud.supergame.behaviortree;

import com.facundolinlaud.supergame.behaviortree.stack.Value;

import java.util.LinkedList;
import java.util.Stack;

public class ParallelTask<T extends Blackboard> extends CompositeTask<T> {
    public ParallelTask(LinkedList<Task> children) {
        super(children);
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
        children.remove(child);

        if(children.isEmpty())
            completed();
    }

    @Override
    void childFailed(Task child) {
        failed();
    }

    @Override
    protected void onStackAvailable(Stack<Value> stack) {}
}
