package com.facundolinlaud.supergame.utils.strategy.impl;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.utils.strategy.RenderPositionStrategy;

/**
 * Created by facundo on 4/1/16.
 */
public class SpriteRenderPositionStrategyImpl implements RenderPositionStrategy {
    @Override
    public Vector2 process(float x, float y) {
        return new Vector2(x - 0.5f, y);
    }
}
