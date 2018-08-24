package com.facundolinlaud.supergame.strategies.skills.castingrequest;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;

public class KeyPressThenClickCastingRequestStrategy extends BaseCastingRequestStrategy {
    @Override
    public void proceedWithCasting(Entity caster, Skill skill) {
        if(isCasterInRange(caster, skill))
            cast(caster, skill);
    }

    private boolean isCasterInRange(Entity caster, Skill skill){
        return true;
    }
}
