package com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck.AreaOfEffectCheckStrategy;

public class CircleAreaOfEffectCheckStrategyStrategyImpl implements AreaOfEffectCheckStrategy {
    private float x;
    private float y;
    private float radius;

    public CircleAreaOfEffectCheckStrategyStrategyImpl(Vector2 pos, float radius) {
        this.x = pos.x;
        this.y = pos.y;
        this.radius = radius;
    }

    @Override
    public boolean isInArea(Vector2 pos) {
        return isInArea(pos.x, pos.y);
    }

    @Override
    public boolean isInArea(float checkX, float checkY) {
        return Math.pow(checkX - x, 2) + Math.pow(checkY - y, 2) <= Math.pow(radius, 2);
    }
}
