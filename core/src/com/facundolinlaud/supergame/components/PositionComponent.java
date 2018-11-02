package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

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

    public PositionComponent(PositionComponent positionComponent) {
        overridePhysicsSystem = true;
        this.x = positionComponent.x;
        this.y = positionComponent.y;
    }

    public PositionComponent(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void set(float x, float y){
        overridePhysicsSystem = true;
        this.x = x;
        this.y = y;
    }

    public Vector2 getPosition() {
        return new Vector2(x, y);
    }

}
