package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by facundo on 3/18/16.
 */
public class RenderComponent implements Component {
    public Texture texture;

    public RenderComponent(Texture texture) {
        this.texture = texture;
    }
}
