package com.facundolinlaud.supergame.strategies.skills.castingrequest;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;

public class KeyPressCastingRequestStrategy extends BaseCastingRequestStrategy {
    @Override
    public void proceedWithCasting(Entity caster, Skill skill) {
        cast(caster, skill);
    }
}
