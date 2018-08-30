package com.facundolinlaud.supergame.ui.model.healthbars;

import com.badlogic.gdx.math.Vector2;

public class HealthBarModel {
    private int health;
    private Vector2 worldPosition;

    public HealthBarModel(int health, Vector2 worldPosition) {
        this.health = health;
        this.worldPosition = worldPosition;
    }

    public int getHealth() {
        return health;
    }

    public Vector2 getWorldPosition() {
        return worldPosition;
    }
}
