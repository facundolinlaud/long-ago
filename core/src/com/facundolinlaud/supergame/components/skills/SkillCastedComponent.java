package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.model.skill.AreaOfEffect;
import com.facundolinlaud.supergame.model.skill.BasicSkill;
import com.facundolinlaud.supergame.model.skill.MeleeSkill;
import com.facundolinlaud.supergame.model.skill.SkillType;

public class SkillCastedComponent<T extends BasicSkill> implements Component {
    public T skill;
    public SkillType skillType;
    public Vector2 epicenter;

    public SkillCastedComponent(T skill, SkillType skillType, Vector2 epicenter) {
        this.skill = skill;
        this.skillType = skillType;
        this.epicenter = epicenter;
    }
}
