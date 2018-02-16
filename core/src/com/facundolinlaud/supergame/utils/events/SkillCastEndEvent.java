package com.facundolinlaud.supergame.utils.events;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.model.skill.AreaOfEffect;

public class SkillCastEndEvent extends Event {
    private Entity caster;
    private AreaOfEffect areaOfEffect;
    private int areaOfEffectSize;
    private Vector2 skillEffectEpicenter;
    int damage;

    public SkillCastEndEvent(Entity caster, AreaOfEffect areaOfEffect, int areaOfEffectSize, Vector2 skillEffectEpicenter, int damage) {
        this.caster = caster;
        this.areaOfEffect = areaOfEffect;
        this.areaOfEffectSize = areaOfEffectSize;
        this.skillEffectEpicenter = skillEffectEpicenter;
        this.damage = damage;
    }

    public Entity getCaster() {
        return caster;
    }

    public AreaOfEffect getAreaOfEffect() {
        return areaOfEffect;
    }

    public int getAreaOfEffectSize() {
        return areaOfEffectSize;
    }

    public Vector2 getSkillEffectEpicenter() {
        return skillEffectEpicenter;
    }

    public int getDamage() {
        return damage;
    }
}
