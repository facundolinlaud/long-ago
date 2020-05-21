package com.facundolinlaud.supergame.behaviortree;

public abstract class BranchTask<T extends Blackboard> extends Task<T> {
    abstract void childCompleted(Task child);

    abstract void childFailed(Task child);
}
