package com.facundolinlaud.supergame.behaviortree;

public abstract class Task<T extends Blackboard> {
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
