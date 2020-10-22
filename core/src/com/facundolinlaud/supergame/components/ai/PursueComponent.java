package com.facundolinlaud.supergame.components.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class PursueComponent implements Component {
    private Entity pursuable;
    private float seekedProximity;
    private Runnable onArrive;
    private Runnable onFail;

    public PursueComponent(Entity pursuable, float seekedProximity, Runnable onArrive, Runnable onFail) {
        this.pursuable = pursuable;
        this.seekedProximity = seekedProximity;
        this.onArrive = onArrive;
        this.onFail = onFail;
    }

    public Entity getPursuable() {
        return pursuable;
    }

    public Runnable getOnArrive() {
        return this.onArrive;
    }

    public Runnable getOnFail() {
        return onFail;
    }

    public float getSeekedProximity() {
        return seekedProximity;
    }
}
