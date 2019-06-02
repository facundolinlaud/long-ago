package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.skills.castingrequest.KeyPressCastingRequestStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

public class KeyPressSkillCastingRequestSystem extends IteratingSystem {
    private ComponentMapper<SkillCastingRequestComponent> scrm = Mappers.skillCastingRequest;

    private KeyPressCastingRequestStrategy requestStrategy;

    public KeyPressSkillCastingRequestSystem() {
        super(Family.all(SkillCastingRequestComponent.class).exclude(SkillClickComponent.class).get());
        this.requestStrategy = new KeyPressCastingRequestStrategy();
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillCastingRequestComponent requestComponent = scrm.get(caster);
        Skill skill = requestComponent.getRequestedSkill();

        this.requestStrategy.attemptToCast(caster, skill);
    }
}
