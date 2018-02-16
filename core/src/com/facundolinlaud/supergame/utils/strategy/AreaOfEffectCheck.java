package com.facundolinlaud.supergame.utils.strategy;

import com.badlogic.gdx.math.Vector2;

public interface AreaOfEffectCheck {
    boolean isInArea(Vector2 pos);

    boolean isInArea(int xCheck, int yCheck);
}
