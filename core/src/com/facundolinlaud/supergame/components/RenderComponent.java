package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.facundolinlaud.supergame.model.RenderPriority;
import com.facundolinlaud.supergame.strategies.renderposition.RenderPositionStrategy;
import com.facundolinlaud.supergame.strategies.renderposition.DefaultRenderPositionStrategyImpl;

/**
 * Created by facundo on 3/18/16.
 */
public class RenderComponent implements Component {
    public TextureRegion texture;
    public RenderPriority priority;
    public RenderPositionStrategy renderPositionStrategy;

    {
        this.priority = RenderPriority.createNormalRenderPriority();
        this.renderPositionStrategy = new DefaultRenderPositionStrategyImpl();
    }

    public RenderComponent(TextureRegion texture, RenderPositionStrategy renderPositionStrategy) {
        this.texture = texture;
        this.renderPositionStrategy = renderPositionStrategy;
    }

    public RenderComponent(TextureRegion texture, RenderPriority priority) {
        this.texture = texture;
        this.priority = priority;
    }

    public RenderComponent(TextureRegion texture) {
        this.texture = texture;
    }

    public RenderComponent(RenderPositionStrategy renderPositionStrategy) {
        this.renderPositionStrategy = renderPositionStrategy;
    }
}
