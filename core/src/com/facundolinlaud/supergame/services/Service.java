package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.Engine;

public abstract class Service {
    private Engine engine;

    public Service(Engine engine) {
        this.engine = engine;
    }

    protected Engine getEngine() {
        return engine;
    }
}
