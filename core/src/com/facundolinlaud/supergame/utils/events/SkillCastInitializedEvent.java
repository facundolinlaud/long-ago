package com.facundolinlaud.supergame.utils.events;

import com.badlogic.ashley.core.Entity;

public class SkillCastInitializedEvent extends Event {
    private Entity caster;
    private float castTime;
    private String skillName;

    public SkillCastInitializedEvent(Entity caster, float castTime, String skillName) {
        this.caster = caster;
        this.castTime = castTime;
        this.skillName = skillName;
    }

    public Entity getCaster() {
        return caster;
    }

    public float getCastTime() {
        return castTime;
    }

    public String getSkillName() {
        return skillName;
    }
}
