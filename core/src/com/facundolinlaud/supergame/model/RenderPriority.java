package com.facundolinlaud.supergame.model;

/**
 * Created by facundo on 3/19/16.
 */
public enum RenderPriority {
    PARTICLE(1),
    AGENT(2),
    ITEM(3),
    DEAD_AGENT(4);

    private int value;

    RenderPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
