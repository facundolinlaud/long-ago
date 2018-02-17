package com.facundolinlaud.supergame.strategies.impl;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.strategies.AreaOfEffectCheckStrategy;

public class SquareAreaOfEffectCheckStrategyStrategyImpl implements AreaOfEffectCheckStrategy {
    private int x;
    private int y;
    private int sideSize;

    public SquareAreaOfEffectCheckStrategyStrategyImpl(Vector2 pos, int sideSize) {
        this.x = (int) pos.x;
        this.y = (int) pos.y;
        this.sideSize = sideSize;
    }

    @Override
    public boolean isInArea(Vector2 pos) {
        return isInArea((int) pos.x, (int) pos.y);
    }

    @Override
    public boolean isInArea(int xCheck, int yCheck) {
        return x <= xCheck && xCheck <= x + sideSize &&
               y <= yCheck && yCheck <= y + sideSize;
    }
}
