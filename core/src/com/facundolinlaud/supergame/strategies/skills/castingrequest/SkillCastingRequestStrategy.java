package com.facundolinlaud.supergame.strategies.skills.castingrequest;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.SkillType;

public interface SkillCastingRequestStrategy {
    void attemptToCast(Entity caster, SkillType skillType, int requestedSkillId);
}
