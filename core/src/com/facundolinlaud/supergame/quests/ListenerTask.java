package com.facundolinlaud.supergame.quests;

import com.badlogic.gdx.ai.btree.LeafTask;

public abstract class ListenerTask extends LeafTask {
    @Override
    public Status execute() {
        return Status.RUNNING;
    }
}
