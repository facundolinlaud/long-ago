package com.facundolinlaud.supergame.quests;

import com.facundolinlaud.supergame.quests.composites.CompositeTask;

public abstract class Task<T> {
    protected CompositeTask parent;
    private T blackboard;

    public abstract void activate();

    public void completed() {
        parent.childCompleted(this);
    }

    public void failed(){
        parent.childFailed(this);
    }

    public void setParent(CompositeTask parent) {
        this.parent = parent;
    }

    public void setBlackboard(T blackboard){
        this.blackboard = blackboard;
        onBlackboardAvailable(blackboard);
    }

    public T getBlackboard(){
        return this.blackboard;
    }

    protected void onBlackboardAvailable(T blackboard){}
}
