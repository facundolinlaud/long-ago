package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.facundolinlaud.supergame.model.RenderPriority;
import com.facundolinlaud.supergame.strategies.renderposition.RenderPositionStrategy;
import com.facundolinlaud.supergame.strategies.renderposition.DefaultRenderPositionStrategyImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facundo on 3/18/16.
 */
public class RenderComponent implements Component {
    private static final float DEFAULT_ROTATION = 0f;

    private List<TextureRegion> regions;
    private RenderPriority priority;
    private RenderPositionStrategy renderPositionStrategy;
    private float rotation;

    {
        this.renderPositionStrategy = new DefaultRenderPositionStrategyImpl();
        this.rotation = DEFAULT_ROTATION;
    }

    public RenderComponent(TextureRegion region, RenderPriority priority) {
        this.regions = new ArrayList();
        this.regions.add(region);
        this.priority = priority;
    }

    public RenderComponent(RenderPositionStrategy renderPositionStrategy, RenderPriority priority) {
        this.renderPositionStrategy = renderPositionStrategy;
        this.regions = new ArrayList();
        this.priority = priority;
    }

    public void setRotation(float rotation){
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public List<TextureRegion> getRegions() {
        return regions;
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
        this.regions.clear();
    }

    public void add(TextureRegion region){
        this.regions.add(region);
    }
}
