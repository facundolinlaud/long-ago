package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by facundo on 3/18/16.
 */
public class InputComponent implements Component {
    public Vector2 direction;

    public InputComponent() {
        direction = new Vector2();
    }

    public InputComponent(Vector2 direction) {
        this.direction = direction;
    }
}
