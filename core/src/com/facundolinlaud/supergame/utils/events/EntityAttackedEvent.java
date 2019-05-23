package com.facundolinlaud.supergame.utils.events;

import com.badlogic.ashley.core.Entity;

public class EntityAttackedEvent extends Event {
    private Entity victim;
    private Entity attacker;
    private float damage;

    public EntityAttackedEvent(Entity victim, Entity attacker, float damage) {
        this.victim = victim;
        this.attacker = attacker;
        this.damage = damage;
    }

    public Entity getVictim() {
        return victim;
    }

    public Entity getAttacker() {
        return attacker;
    }

    public float getDamage() {
        return damage;
    }
}
