package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.strategies.health.ZeroHealthStrategy;
import com.facundolinlaud.supergame.strategies.health.NPCZeroHealthStrategyImpl;

/**
 * Created by facundo on 3/23/16.
 */
public class HealthComponent implements Component {
    public static final int DEFAULT_HEALTH = 100;

    public float totalHealth;
    public float currentHealth;
    public ZeroHealthStrategy zeroHealthStrategy;

    public HealthComponent() {
        this.totalHealth = DEFAULT_HEALTH;
        this.currentHealth = DEFAULT_HEALTH;
        this.zeroHealthStrategy = new NPCZeroHealthStrategyImpl();
    }

    public HealthComponent(float totalHealth, float currentHealth) {
        this.totalHealth = totalHealth;
        this.currentHealth = currentHealth;
        this.zeroHealthStrategy = new NPCZeroHealthStrategyImpl();
    }

    public boolean isFull(){
        return totalHealth <= currentHealth;
    }
}
