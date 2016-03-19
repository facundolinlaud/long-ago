package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 3/17/16.
 */
public class PositionComponent implements Component {
    public int x;
    public int y;

    public PositionComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
