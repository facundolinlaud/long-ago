package com.facundolinlaud.supergame.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Pops: nothing
 * Pushes: the stack's top n times
 */
public class RePushValueTask extends Task {
    private static final int DEFAULT_DEEPNESS = 1;

    private int deepness;
    private int times;

    public RePushValueTask(int deepness, int times) {
        this.deepness = deepness;
        this.times = times;
    }

    @Override
    public void activate() {
        Stack<Value> stack = getBlackboard().getStack();
        List<Value> copies = copy(stack, deepness);

        for(int i = 0; i < times; i++){
            copies.forEach(value -> stack.push(value));
        }

        completed();
    }

    private List<Value> copy(Stack<Value> stack, int deepness){
        LinkedList<Value> list = new LinkedList();

        IntStream.range(0, deepness).forEach(i -> list.addFirst(stack.pop()));
        list.forEach(value -> stack.add(value));

        return list;
    }
}
