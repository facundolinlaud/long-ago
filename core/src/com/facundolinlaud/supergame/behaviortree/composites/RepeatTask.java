package com.facundolinlaud.supergame.behaviortree.composites;

import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.LinkedList;

public class RepeatTask<T extends Blackboard> extends SequentialTask<T> {
    private int times;
    private int current = 0;

    public RepeatTask(LinkedList children, int times) {
        super(children);
        this.times = times;
        this.current = 0;
    }


    @Override
    public void childCompleted(Task child) {
        if (childrenIterator.hasNext()) {
            activate();
        } else {
            if (current < times) {
                super.reset();
                activate();
            } else {
                completed();
            }
        }
    }

    @Override
    public void activate() {
        current++;
        super.activate();
    }

    @Override
    public void reset() {
        super.reset();
        current = 0;
    }
}
