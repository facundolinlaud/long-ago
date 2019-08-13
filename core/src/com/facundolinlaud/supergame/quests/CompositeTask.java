package com.facundolinlaud.supergame.quests;

import java.util.LinkedList;

public abstract class CompositeTask extends Task {
    protected LinkedList<Task> children;

    public CompositeTask(LinkedList<Task> children) {
        this.children = children;
        children.forEach(child -> child.setParent(this));
    }

    abstract void childCompleted(Task child);
}
