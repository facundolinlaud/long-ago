package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 3/31/16.
 */
public class AttackComponent implements Component {
    private static final int DEFAULT_ATTACK = 1;
    private static final float DEFAULT_COOLDOWN_IN_SECONDS = 0.5f;

    public int attack;
    public float cooldownTime;

    public AttackComponent() {
        this.attack = DEFAULT_ATTACK;
        this.cooldownTime = DEFAULT_COOLDOWN_IN_SECONDS;
    }

    public AttackComponent(int attack, float cooldownTime) {
        this.attack = attack;
        this.cooldownTime = cooldownTime;
    }
}
