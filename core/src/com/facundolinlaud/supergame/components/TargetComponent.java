package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TargetComponent implements Component {
    private Vector2 position;
    private boolean isClicking;

    public TargetComponent() {}

    public TargetComponent(Vector2 position) {
        this.position = position;
        this.isClicking = true;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setClicking(boolean isClicking){
        this.isClicking = isClicking;
    }

    public boolean isClicking() {
        return isClicking;
    }
}
