package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by facundo on 3/18/16.
 */
public class RenderComponent implements Component {
    public TextureRegion texture;

    public RenderComponent() {}

    public RenderComponent(TextureRegion texture) {
        this.texture = texture;
    }
}
