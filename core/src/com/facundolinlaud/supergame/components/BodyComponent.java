package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by facundo on 3/20/16.
 */
public class BodyComponent implements Component {
    public Body body;

    public BodyComponent(Body body) {
        this.body = body;
    }
}
