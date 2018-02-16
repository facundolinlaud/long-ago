package com.facundolinlaud.supergame.utils.events;

import com.badlogic.ashley.core.Entity;

public class SkillCastRequestedEvent extends Event {
    private Entity caster;
    private int skillId;

    public SkillCastRequestedEvent(Entity caster, int skillId) {
        this.caster = caster;
        this.skillId = skillId;
    }

    public Entity getCaster() {
        return caster;
    }

    public int getSkillId() {
        return skillId;
    }
}
