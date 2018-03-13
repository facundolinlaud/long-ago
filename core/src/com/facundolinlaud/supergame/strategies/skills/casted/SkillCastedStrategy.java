package com.facundolinlaud.supergame.strategies.skills.casted;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.BasicSkill;

public interface SkillCastedStrategy<T extends BasicSkill> {
    void execute(Entity caster, T skill);
}
