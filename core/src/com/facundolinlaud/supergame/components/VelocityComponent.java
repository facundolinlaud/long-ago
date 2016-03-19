package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 3/17/16.
 */
public class VelocityComponent implements Component {
    public float velocity;

    public VelocityComponent(float velocity) {
        this.velocity = velocity;
    }
}
