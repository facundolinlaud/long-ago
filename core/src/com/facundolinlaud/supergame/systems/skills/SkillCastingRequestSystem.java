package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillTrigger;
import com.facundolinlaud.supergame.strategies.skills.castingrequest.BaseCastingRequestStrategy;
import com.facundolinlaud.supergame.strategies.skills.castingrequest.KeyPressCastingRequestStrategy;
import com.facundolinlaud.supergame.strategies.skills.castingrequest.KeyPressThenClickCastingRequestStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.HashMap;
import java.util.Map;

public class SkillCastingRequestSystem extends IteratingSystem {
    private ComponentMapper<SkillCastingRequestComponent> scrm = Mappers.skillCastingRequest;

    private AvailableSkillsFactory skillsFactory;
    private Map<SkillTrigger, BaseCastingRequestStrategy> castingRequestStrategies;

    public SkillCastingRequestSystem(AvailableSkillsFactory skillsFactory) {
        super(Family.all(SkillCastingRequestComponent.class).get());
        this.skillsFactory = skillsFactory;

        this.castingRequestStrategies = new HashMap<>();
        this.castingRequestStrategies.put(SkillTrigger.KEY_PRESS, new KeyPressCastingRequestStrategy());
        this.castingRequestStrategies.put(SkillTrigger.KEY_PRESS_THEN_CLICK, new KeyPressThenClickCastingRequestStrategy());
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillCastingRequestComponent skillCastingRequestComponent = scrm.get(caster);
        int requestedSkillId = skillCastingRequestComponent.requestedSkillId;
        Skill skill = skillsFactory.getSkillById(requestedSkillId);

        this.castingRequestStrategies.get(skill.getSkillTrigger()).attemptToCast(caster, skill);
    }
}
