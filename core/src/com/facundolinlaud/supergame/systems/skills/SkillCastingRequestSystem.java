package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.model.skill.BasicSkill;
import com.facundolinlaud.supergame.model.skill.SkillType;
import com.facundolinlaud.supergame.utils.Mappers;

public class SkillCastingRequestSystem extends IteratingSystem {
    private ComponentMapper<SkillCastingRequestComponent> scrm = Mappers.skillCastingRequest;

    private AvailableSkillsFactory availableSkillsFactory;

    public SkillCastingRequestSystem(AvailableSkillsFactory availableSkillsFactory) {
        super(Family.all(SkillCastingRequestComponent.class).get());
        this.availableSkillsFactory = availableSkillsFactory;
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        if(canCast(caster))
            proceedWithCasting(caster);

        caster.remove(SkillCastingRequestComponent.class);
    }

    private boolean canCast(Entity caster) {
        return true;
    }

    private void proceedWithCasting(Entity caster) {
        SkillCastingRequestComponent skillCastingRequestComponent = scrm.get(caster);
        int requestedSkillId = skillCastingRequestComponent.requestedSkillId;
        SkillType skillType = availableSkillsFactory.getSkillTypeById(requestedSkillId);
        BasicSkill basicSkill = availableSkillsFactory.getBasicSkill(requestedSkillId, skillType);

        caster.add(new SkillCastingComponent(basicSkill, skillType));
    }
}
