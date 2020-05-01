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
public class IterateTask extends SequentialTask {
    private boolean isFirstActivate;
    private Stack<Value> iterables;

    public IterateTask(LinkedList<Task> children) {
        super(children);
        this.isFirstActivate = true;
        this.iterables = new Stack();
    }

    @Override
    public void activate() {
        if (isFirstActivate) {
            popIterablesFromStack();

            if(iterables.isEmpty()){
                completed();
                return;
            }

            isFirstActivate = false;
            Value poped = iterables.pop();
            getBlackboard().getStack().add(poped);
        }

        super.activate();
    }

    @Override
    public void childCompleted(Task child) {
        if (children.isEmpty() && !iterables.isEmpty()) {
            getBlackboard().getStack().add(iterables.pop());
        }

        super.childCompleted(child);
    }

    private void popIterablesFromStack() {
        Stack<Value> stack = getBlackboard().getStack();
        int n = stack.pop().getFloatValue().intValue();
        IntStream.range(0, n).forEach(i -> iterables.add(stack.pop()));
    }
}
