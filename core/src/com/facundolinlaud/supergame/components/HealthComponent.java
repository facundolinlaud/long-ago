package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 3/23/16.
 */
public class HealthComponent implements Component {
    public static final int DEFAULT_HEALTH = 100;

    public float health;

    public HealthComponent() {
        this.health = DEFAULT_HEALTH;
    }

    public HealthComponent(float health) {
        this.health = health;
    }
}
