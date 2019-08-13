package com.facundolinlaud.supergame.quests;

public abstract class Task {
    protected CompositeTask parent;

    abstract void activate();

    public void completed() {
        parent.childCompleted(this);
    }

    public void setParent(CompositeTask parent) {
        this.parent = parent;
    }
}
