package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.facundolinlaud.supergame.utils.RenderPriority;
import com.facundolinlaud.supergame.utils.strategy.RenderPositionStrategy;
import com.facundolinlaud.supergame.utils.strategy.impl.DefaultRenderPositionStrategyImpl;

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

    public RenderComponent(TextureRegion texture, RenderPriority priority) {
        this.texture = texture;
        this.priority = priority;
    }

    public RenderComponent(RenderPositionStrategy renderPositionStrategy) {
        this.renderPositionStrategy = renderPositionStrategy;
    }
}
