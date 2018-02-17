package com.facundolinlaud.supergame.strategies.impl;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.strategies.AreaOfEffectCheckStrategy;

public class CircleAreaOfEffectCheckStrategyStrategyImpl implements AreaOfEffectCheckStrategy {
    private int x;
    private int y;
    private int radius;

    public CircleAreaOfEffectCheckStrategyStrategyImpl(Vector2 pos, int radius) {
        this.x = (int) pos.x;
        this.y = (int) pos.y;
        this.radius = radius;
    }

    @Override
    public boolean isInArea(Vector2 pos) {
        return isInArea((int) pos.x, (int) pos.y);
    }

    @Override
    public boolean isInArea(int checkX, int checkY) {
        return Math.pow(checkX - x, 2) + Math.pow(checkY - y, 2) <= Math.pow(radius, 2);
    }
}
