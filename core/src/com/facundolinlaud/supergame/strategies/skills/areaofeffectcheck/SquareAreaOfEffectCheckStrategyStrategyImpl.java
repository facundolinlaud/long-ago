package com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck.AreaOfEffectCheckStrategy;

public class SquareAreaOfEffectCheckStrategyStrategyImpl implements AreaOfEffectCheckStrategy {
    private float x;
    private float y;
    private float sideSize;

    public SquareAreaOfEffectCheckStrategyStrategyImpl(Vector2 pos, float sideSize) {
        this.x = pos.x;
        this.y = pos.y;
        this.sideSize = sideSize;
    }

    @Override
    public boolean isInArea(Vector2 pos) {
        return isInArea(pos.x, pos.y);
    }

    @Override
    public boolean isInArea(float xCheck, float yCheck) {
        return x <= xCheck && xCheck <= x + sideSize &&
               y <= yCheck && yCheck <= y + sideSize;
    }
}
