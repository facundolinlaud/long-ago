package com.facundolinlaud.supergame.strategies.skills.castingrequest;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillType;

public interface SkillCastingRequestStrategy {
    void proceedWithCasting(Entity caster, Skill skill);
}
