package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.facundolinlaud.supergame.model.RenderPriority;
import com.facundolinlaud.supergame.strategies.renderposition.DefaultRenderPositionStrategyImpl;
import com.facundolinlaud.supergame.strategies.renderposition.RenderPositionStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facundo on 3/18/16.
 */
public class RenderComponent implements Component {
    private List<Sprite> sprites;
    private RenderPriority priority;
    private RenderPositionStrategy renderPositionStrategy;

    {
        this.renderPositionStrategy = new DefaultRenderPositionStrategyImpl();
    }

    public RenderComponent(Sprite sprite, RenderPriority priority) {
        this.sprites = new ArrayList();
        this.sprites.add(sprite);
        this.priority = priority;
    }

    public RenderComponent(RenderPositionStrategy renderPositionStrategy, RenderPriority priority) {
        this.renderPositionStrategy = renderPositionStrategy;
        this.sprites = new ArrayList();
        this.priority = priority;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public RenderPriority getPriority() {
        return priority;
    }

    public void setPriority(RenderPriority priority) {
        this.priority = priority;
    }

    public RenderPositionStrategy getRenderPositionStrategy() {
        return renderPositionStrategy;
    }

    public void clear() {
        this.sprites.clear();
    }

    public void add(Sprite sprite){
        this.sprites.add(sprite);
    }
}
