package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillType;

public class SkillTargetedComponent implements Component {
    public Entity caster;
    public Skill skill;

    public SkillTargetedComponent(Entity caster, Skill skill) {
        this.caster = caster;
        this.skill = skill;
    }
}
