package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.facundolinlaud.supergame.helper.RenderPriority;

/**
 * Created by facundo on 3/18/16.
 */
public class RenderComponent implements Component {
    public TextureRegion texture;
    public RenderPriority priority;

    public RenderComponent() {}

    public RenderComponent(TextureRegion texture) {
        this.texture = texture;
        this.priority = RenderPriority.createNormalRenderPriority();
    }

    public RenderComponent(TextureRegion texture, RenderPriority priority) {
        this.texture = texture;
        this.priority = priority;
    }
}
