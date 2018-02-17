package com.facundolinlaud.supergame.strategies;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.BasicSkill;

public interface SkillCastStrategy<T extends BasicSkill> {
    void handleSkill(Entity caster, int skillId);
}
