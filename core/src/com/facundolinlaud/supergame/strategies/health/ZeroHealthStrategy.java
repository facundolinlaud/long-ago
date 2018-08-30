package com.facundolinlaud.supergame.strategies.health;

import com.badlogic.ashley.core.Entity;

public interface ZeroHealthStrategy {
    void onZeroHealth(Entity entity);
}
