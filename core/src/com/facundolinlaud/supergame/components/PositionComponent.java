package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 3/17/16.
 */
public class PositionComponent implements Component {
    public boolean overridePhysicsSystem;
    public float x;
    public float y;

    public PositionComponent() {}

    public PositionComponent(float x, float y) {
        overridePhysicsSystem = true;
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y){
        overridePhysicsSystem = true;
        this.x = x;
        this.y = y;
    }
}
