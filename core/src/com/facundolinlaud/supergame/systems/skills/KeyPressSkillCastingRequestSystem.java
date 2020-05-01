package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.managers.world.SkillsManager;
import com.facundolinlaud.supergame.utils.Mappers;

public class KeyPressSkillCastingRequestSystem extends CastingRequestSystem {
    private ComponentMapper<SkillCastingRequestComponent> scrm = Mappers.skillCastingRequest;

    private SkillsManager skillsManager;

    public KeyPressSkillCastingRequestSystem(SkillsManager skillsManager) {
        super(Family.all(SkillCastingRequestComponent.class).exclude(SkillClickComponent.class).get());
        this.skillsManager = skillsManager;
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
//        skillsManager.requestCasting(caster, "Blow");
//        SkillCastingRequestComponent requestComponent = scrm.get(caster);
//        Skill skill = requestComponent.getRequestedSkill();
//
//        if(canCast(caster, skill))
//            cast(caster, skill);
//        else
//            rejectRequest(caster, skill);
    }
}
