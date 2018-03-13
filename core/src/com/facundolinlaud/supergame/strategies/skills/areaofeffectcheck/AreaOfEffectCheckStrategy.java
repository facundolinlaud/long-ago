package com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck;

import com.badlogic.gdx.math.Vector2;

public interface AreaOfEffectCheckStrategy {
    boolean isInArea(Vector2 pos);

    boolean isInArea(float xCheck, float yCheck);
}
