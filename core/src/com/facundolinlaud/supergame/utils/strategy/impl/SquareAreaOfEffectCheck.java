package com.facundolinlaud.supergame.utils.strategy.impl;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.utils.strategy.AreaOfEffectCheck;

public class SquareAreaOfEffectCheck implements AreaOfEffectCheck {
    private int x;
    private int y;
    private int sideSize;

    public SquareAreaOfEffectCheck(Vector2 pos, int sideSize) {
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
