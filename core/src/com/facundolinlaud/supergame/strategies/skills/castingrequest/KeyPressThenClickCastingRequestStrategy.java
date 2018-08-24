package com.facundolinlaud.supergame.strategies.skills.castingrequest;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.model.skill.RangedSkill;
import com.facundolinlaud.supergame.model.skill.SkillType;

public class KeyPressThenClickCastingRequestStrategy implements SkillCastingRequestStrategy {
    private AvailableSkillsFactory skillsFactory;

    public KeyPressThenClickCastingRequestStrategy(AvailableSkillsFactory skillsFactory) {
        this.skillsFactory = skillsFactory;
    }

    @Override
    public void attemptToCast(Entity caster, SkillType skillType, int requestedSkillId) {
        RangedSkill basicSkill = skillsFactory.getRangedSkill(requestedSkillId);
        caster.add(new SkillCastingComponent(basicSkill, skillType));
        caster.remove(SkillCastingRequestComponent.class);


    }
}
