package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.strategies.health.ZeroHealthStrategy;
import com.facundolinlaud.supergame.strategies.health.NPCZeroHealthStrategyImpl;

/**
 * Created by facundo on 3/23/16.
 */
public class HealthComponent implements Component {
    private float totalHealth;
    private float currentHealth;
    private ZeroHealthStrategy zeroHealthStrategy;

    public HealthComponent(float totalHealth, float currentHealth) {
        this.totalHealth = totalHealth;
        this.currentHealth = currentHealth;
        this.zeroHealthStrategy = new NPCZeroHealthStrategyImpl();
    }

    public boolean isFull(){
        return totalHealth <= currentHealth;
    }

    public void decrease(float damageDealt) {
        this.currentHealth -= damageDealt;
    }

    public float getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(float totalHealth) {
        this.totalHealth = totalHealth;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(float currentHealth) {
        this.currentHealth = currentHealth;
    }

    public ZeroHealthStrategy getZeroHealthStrategy() {
        return zeroHealthStrategy;
    }

    public void setZeroHealthStrategy(ZeroHealthStrategy zeroHealthStrategy) {
        this.zeroHealthStrategy = zeroHealthStrategy;
    }
}
