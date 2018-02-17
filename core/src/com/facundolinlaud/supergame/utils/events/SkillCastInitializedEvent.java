package com.facundolinlaud.supergame.utils.events;

import com.badlogic.gdx.math.Vector2;

public class SkillCastInitializedEvent extends Event {
    private Vector2 casterPosition;
    private float castTime;
    private String skillName;

    public SkillCastInitializedEvent(Vector2 casterPosition, float castTime, String skillName) {
        this.casterPosition = casterPosition;
        this.castTime = castTime;
        this.skillName = skillName;
    }

    public Vector2 getCasterPosition() {
        return casterPosition;
    }

    public float getCastTime() {
        return castTime;
    }

    public String getSkillName() {
        return skillName;
    }
}
