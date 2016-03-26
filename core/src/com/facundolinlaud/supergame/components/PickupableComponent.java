package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 3/25/16.
 */
public class PickupableComponent implements Component {
    private final static float DEFAULT_WEIGHT = 1f;

    public float weight;

    public PickupableComponent() {
        this.weight = DEFAULT_WEIGHT;
    }

    public PickupableComponent(float weight) {
        this.weight = weight;
    }
}
