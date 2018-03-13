package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.BasicSkill;
import com.facundolinlaud.supergame.model.skill.SkillType;

public class SkillTargetedComponent implements Component {
    public Entity caster;
    public BasicSkill basicSkill;
    public SkillType skillType;

    public SkillTargetedComponent(Entity caster, BasicSkill basicSkill, SkillType skillType) {
        this.caster = caster;
        this.basicSkill = basicSkill;
        this.skillType = skillType;
    }
}
