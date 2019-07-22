package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.utils.Mappers;

public class KeyPressSkillCastingRequestSystem extends CastingRequestSystem {
    private ComponentMapper<SkillCastingRequestComponent> scrm = Mappers.skillCastingRequest;


    public KeyPressSkillCastingRequestSystem() {
        super(Family.all(SkillCastingRequestComponent.class).exclude(SkillClickComponent.class).get());
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillCastingRequestComponent requestComponent = scrm.get(caster);
        Skill skill = requestComponent.getRequestedSkill();

        if(canCast(caster, skill))
            cast(caster, skill);
        else
            rejectRequest(caster, skill);
    }
}
