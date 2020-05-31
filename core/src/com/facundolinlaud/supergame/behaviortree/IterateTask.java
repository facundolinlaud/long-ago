package com.facundolinlaud.supergame.behaviortree;

import com.facundolinlaud.supergame.behaviortree.stack.Value;

import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Receives n + 1 elements pushed in the stack as parameter, where the first n elements are the
 * elements to be iterated over, and the last one is an integer of value n, that indicates how
 * many elements there are to be iterated over. This task consumes all of these parameters upon
 * activation.
 * <p>
 * Before sequentially activating the children, this task pushes to the stack the current element
 * being iterated.
 */
public class IterateTask<T extends Blackboard> extends CompositeTask<T> {
    private Stack<Value> iterables;

    public IterateTask(LinkedList<Task> children) {
        super(children);
        iterables = new Stack();
    }

    @Override
    public void activate() {
        popIterablesFromStack();

        if (!iterables.isEmpty()) nextCycle();
        else completed();
    }

    private void nextCycle() {
        popNextValueIntoStack();
        reset();
        childrenIterator.next().activate();
    }

    private void popNextValueIntoStack() {
        stack.add(iterables.pop());
    }

    @Override
    public void childCompleted(Task child) {
        if (isCurrentCycleOver()) {
            if (iterables.isEmpty()) {
                completed();
            } else {
                nextCycle();
            }
        } else {
            childrenIterator.next().activate();
        }
    }

    private boolean isCurrentCycleOver() {
        return !childrenIterator.hasNext();
    }

    private void popIterablesFromStack() {
        int n = stack.pop().getFloat().intValue();

        IntStream.range(0, n).forEach(i -> iterables.add(stack.pop()));
    }

    @Override
    void childFailed(Task child) {
        failed();
    }
}
