package com.facundolinlaud.supergame.strategies.skills.castingrequest;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.model.skill.Skill;

public abstract class BaseCastingRequestStrategy implements SkillCastingRequestStrategy {
    public void attemptToCast(Entity caster, Skill skill){
        if(hasMana(caster)){
            proceedWithCasting(caster, skill);
        }
    }

    protected void cast(Entity caster, Skill skill){
        caster.add(new SkillCastingComponent(skill));
        caster.remove(SkillCastingRequestComponent.class);
    }

    // TODO
    private boolean hasMana(Entity caster){
        return true;
    }
}
