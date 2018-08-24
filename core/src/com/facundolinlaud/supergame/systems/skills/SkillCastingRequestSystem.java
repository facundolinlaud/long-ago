package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.model.skill.SkillType;
import com.facundolinlaud.supergame.strategies.skills.castingrequest.KeyPressCastingRequestStrategy;
import com.facundolinlaud.supergame.strategies.skills.castingrequest.SkillCastingRequestStrategy;
import com.facundolinlaud.supergame.strategies.skills.castingrequest.KeyPressThenClickCastingRequestStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.HashMap;
import java.util.Map;

public class SkillCastingRequestSystem extends IteratingSystem {
    private ComponentMapper<SkillCastingRequestComponent> scrm = Mappers.skillCastingRequest;

    private AvailableSkillsFactory skillsFactory;
    private Map<SkillType, SkillCastingRequestStrategy> castingStrategies;

    public SkillCastingRequestSystem(AvailableSkillsFactory skillsFactory) {
        super(Family.all(SkillCastingRequestComponent.class).get());
        this.skillsFactory = skillsFactory;

        this.castingStrategies = new HashMap<>();
        this.castingStrategies.put(SkillType.MELEE_SKILL, new KeyPressCastingRequestStrategy(skillsFactory));
        this.castingStrategies.put(SkillType.RANGED_SKILL, new KeyPressThenClickCastingRequestStrategy(skillsFactory));
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillCastingRequestComponent skillCastingRequestComponent = scrm.get(caster);
        int requestedSkillId = skillCastingRequestComponent.requestedSkillId;
        SkillType skillType = skillsFactory.getSkillTypeById(requestedSkillId);

        this.castingStrategies.get(skillType).attemptToCast(caster, skillType, requestedSkillId);
    }
}
