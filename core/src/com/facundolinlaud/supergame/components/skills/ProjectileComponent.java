package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.model.skill.Skill;

public class ProjectileComponent implements Component {
    private Entity caster;
    private Skill skill;
    private Vector2 origin;

    public ProjectileComponent(Entity caster, Skill skill, Vector2 origin) {
        this.caster = caster;
        this.skill = skill;
        this.origin = origin;
    }

    public Entity getCaster() {
        return caster;
    }

    public Skill getSkill() {
        return skill;
    }

    public Vector2 getOrigin() {
        return origin;
    }
}
