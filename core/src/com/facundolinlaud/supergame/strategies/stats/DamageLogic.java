package com.facundolinlaud.supergame.strategies.stats;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;

public interface DamageLogic {
    float calculateDamageDealt(Entity caster, Entity victim, Skill skill);
}
