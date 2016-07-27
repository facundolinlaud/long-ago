package com.facundolinlaud.supergame.components.sprite;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by facundo on 26/7/16.
 */
public class StackableSpriteComponent implements Component {
    public Texture texture;

    public StackableSpriteComponent(Texture texture) {
        this.texture = texture;
    }
}
