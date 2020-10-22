package com.facundolinlaud.supergame.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.LeafTask;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Pops: nothing
 * Pushes: the stack's top n times
 */
public class RePushValueTask extends LeafTask {
    private int depth;
    private int times;

    public RePushValueTask(int depth, int times) {
        this.depth = depth;
        this.times = times;
    }

    @Override
    public void activate() {
        List<Value> copies = copy(stack, depth);

        for (int i = 0; i < times; i++) {
            copies.forEach(value -> stack.push(value));
        }

        completed();
    }

    private List<Value> copy(Stack<Value> stack, int depth) {
        LinkedList<Value> list = new LinkedList();

        IntStream.range(0, depth).forEach(i -> list.addFirst(stack.pop()));
        list.forEach(value -> stack.add(value));

        return list;
    }
}
