package com.facundolinlaud.supergame.strategies;

import com.badlogic.gdx.math.Vector2;

public interface AreaOfEffectCheckStrategy {
    boolean isInArea(Vector2 pos);

    boolean isInArea(int xCheck, int yCheck);
}
