package com.facundolinlaud.supergame.strategies.impl;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.strategies.RenderPositionStrategy;

/**
 * Created by facundo on 4/1/16.
 */
public class DefaultRenderPositionStrategyImpl implements RenderPositionStrategy {
    @Override
    public Vector2 process(float x, float y) {
        return new Vector2(x, y);
    }
}
