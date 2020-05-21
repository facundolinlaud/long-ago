package com.facundolinlaud.supergame.behaviortree;

import com.facundolinlaud.supergame.behaviortree.stack.Value;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public abstract class CompositeTask<T extends Blackboard> extends BranchTask<T> {
    protected LinkedList<Task> children;
    protected Iterator<Task> childrenIterator;

    public CompositeTask(LinkedList<Task> children) {
        this.children = children;
        children.forEach(child -> child.setParent(this));
        reset();
    }

    @Override
    protected void onBlackboardAvailable(Blackboard blackboard) {
        children.forEach(child -> child.setBlackboard(blackboard));
    }

    @Override
    protected void onStackAvailable(Stack<Value> stack) {
        children.forEach(child -> child.setStack(stack));
    }

    @Override
    public void reset() {
        this.childrenIterator = children.iterator();
        children.forEach(child -> child.reset());
    }
}
