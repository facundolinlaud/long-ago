package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 3/17/16.
 */
public class VelocityComponent implements Component {
    private final static float DEFAULT_MAX = 2f;

    public float max;
    public float velocity;

    public VelocityComponent(float velocity) {
        this.velocity = velocity;
        this.max = DEFAULT_MAX;
    }

    public VelocityComponent(float max, float velocity) {
        this.max = max;
        this.velocity = velocity;
    }
}
