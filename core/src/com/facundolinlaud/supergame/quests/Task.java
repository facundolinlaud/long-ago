package com.facundolinlaud.supergame.quests;

import com.facundolinlaud.supergame.quests.composites.CompositeTask;

public abstract class Task {
    protected CompositeTask parent;
    private Blackboard blackboard;

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

    public void setBlackboard(Blackboard blackboard){
        this.blackboard = blackboard;
        onBlackboardAvailable(blackboard);
    }

    public Blackboard getBlackboard(){
        return this.blackboard;
    }

    protected void onBlackboardAvailable(Blackboard blackboard){}
}
