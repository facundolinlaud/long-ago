package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.Engine;
import com.facundolinlaud.supergame.engine.GameResources;

public abstract class Service {
    private Engine engine;

    public Service(Engine engine) {
        this.engine = engine;
    }

    public Service() {
        this.engine = GameResources.get().getEngine();
    }

    protected Engine getEngine() {
        return engine;
    }
}
