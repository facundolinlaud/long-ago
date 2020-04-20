package com.facundolinlaud.supergame.behaviortree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class PoolableTaskManager {
    protected Set<PoolableTask> poolableTasks;
    private List<PoolableTask> scheduledForRemoval;

    public PoolableTaskManager() {
        this.poolableTasks = new HashSet();
        this.scheduledForRemoval = new LinkedList();
    }

    public void addPoolableTask(PoolableTask poolableTask){
        poolableTasks.add(poolableTask);
    }

    public void removePoolableTask(PoolableTask poolableTask){
        scheduledForRemoval.add(poolableTask);
    }

    public void tick(float delta){
        poolableTasks.forEach(task -> task.tick(delta));
        scheduledForRemoval.forEach(task -> poolableTasks.remove(task));
        scheduledForRemoval.clear();
    }
}
