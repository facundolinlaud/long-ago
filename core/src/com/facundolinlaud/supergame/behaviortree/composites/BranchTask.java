package com.facundolinlaud.supergame.behaviortree.composites;

import com.facundolinlaud.supergame.behaviortree.Task;

public abstract class BranchTask<T extends Blackboard> extends Task<T> {
    public abstract void childCompleted(Task child);

    public abstract void childFailed(Task child);
}
