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
 *
 * Before sequentially activating the children, this task pushes to the stack the current element
 * being iterated.
 */
public class IterateTask extends SequentialTask {
    private boolean started;
    private Stack<Value> iterables;

    public IterateTask(LinkedList<Task> children) {
        super(children);
        this.started = false;
        this.iterables = new Stack();
    }

    @Override
    public void activate() {
        Stack<Value> stack = getBlackboard().getStack();

        if (!started) {
            popIterablesFromStack(stack);
            started = true;
        }

        if (iterables.isEmpty()) {
            completed();
            return;
        }

        stack.add(iterables.pop());
        super.activate();
    }


    private void popIterablesFromStack(Stack<Value> stack) {
        int n = stack.pop().getFloatValue().intValue();
        IntStream.range(1, n).forEach(i -> iterables.add(stack.pop()));
    }
}
