package com.facundolinlaud.supergame.model.particle;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Particle implements Component, Poolable{
    private String file;

    public Particle(String file) {
        this.file = file;
    }

    @Override
    public void reset() {

    }
}
