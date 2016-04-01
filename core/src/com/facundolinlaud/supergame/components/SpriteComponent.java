package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by facundo on 3/31/16.
 */
public class SpriteComponent implements Component {
    public Texture texture;

    public SpriteComponent(Texture texture) {
        this.texture = texture;
    }
}
