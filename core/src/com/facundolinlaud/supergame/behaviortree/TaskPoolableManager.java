package com.facundolinlaud.supergame.behaviortree;

public interface TaskPoolableManager {
    void addPoolableTask(PoolableTask task);

    void removePoolableTask(PoolableTask task);
}
