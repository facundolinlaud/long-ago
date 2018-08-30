package com.facundolinlaud.supergame.strategies.skills.casting;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;

public interface SkillCastingStrategy {
    void executeSkillEffects(Entity caster, Skill skill);
}
