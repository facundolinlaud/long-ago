package com.facundolinlaud.supergame.utils.strategy;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by facundo on 4/1/16.
 */
public interface RenderPositionStrategy {
    Vector2 process(float x, float y);
}
