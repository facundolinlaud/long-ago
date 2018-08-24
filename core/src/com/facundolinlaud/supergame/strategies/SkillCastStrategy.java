package com.facundolinlaud.supergame.strategies;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;

public interface SkillCastStrategy<T extends Skill> {
    void handleSkill(Entity caster, int skillId);
}
