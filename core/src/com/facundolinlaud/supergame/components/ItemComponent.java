package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 3/26/16.
 */
public class ItemComponent implements Component {
    private final static float DEFAULT_WEIGHT = 1f;
    private static int id = 0;

    public String name;
    public float weight;

    public ItemComponent() {
        this.name = "Item #" + id;
        this.weight = DEFAULT_WEIGHT;

        id ++;
    }

    public ItemComponent(String name) {
        this.name = name;
        this.weight = DEFAULT_WEIGHT;
    }

    public ItemComponent(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }
}
