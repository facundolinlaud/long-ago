package com.facundolinlaud.supergame.utils.events;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;

public class SkillCastedEvent extends Event {
    private Entity caster;
    private Skill skill;

    public SkillCastedEvent(Entity caster, Skill skill) {
        this.caster = caster;
        this.skill = skill;
    }

    public Entity getCaster() {
        return caster;
    }

    public Skill getSkill() {
        return skill;
    }
}
