package com.facundolinlaud.supergame.strategies.skills.castingrequest;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.model.skill.MeleeSkill;
import com.facundolinlaud.supergame.model.skill.SkillType;

public class KeyPressCastingRequestStrategy implements SkillCastingRequestStrategy {
    private AvailableSkillsFactory skillsFactory;

    public KeyPressCastingRequestStrategy(AvailableSkillsFactory skillsFactory) {
        this.skillsFactory = skillsFactory;
    }

    @Override
    public void attemptToCast(Entity caster, SkillType skillType, int requestedSkillId) {
        MeleeSkill meleeSkill = skillsFactory.getMeleeSkill(requestedSkillId);
        caster.add(new SkillCastingComponent(meleeSkill, skillType));
        caster.remove(SkillCastingRequestComponent.class);
    }
}
