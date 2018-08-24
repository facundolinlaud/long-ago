package com.facundolinlaud.supergame.strategies.skills.casted;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;

public interface SkillCastedStrategy {
    void execute(Entity caster, Skill skill);
}
