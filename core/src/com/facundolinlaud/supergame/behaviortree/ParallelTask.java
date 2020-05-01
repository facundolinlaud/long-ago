package com.facundolinlaud.supergame.behaviortree;

import java.util.LinkedList;

public class ParallelTask<T extends Blackboard> extends CompositeTask<T> {
    public ParallelTask(LinkedList<Task> children) {
        super(children);
    }

    @Override
    public void childCompleted(Task child) {
        completed.add(child);
        children.remove(child);

        if(children.isEmpty())
            completed();
    }

    @Override
    public void activate() {
        children.forEach(task -> task.activate());
    }
}
