package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.skills.SkillTargetedComponent;
import com.facundolinlaud.supergame.model.skill.BasicSkill;
import com.facundolinlaud.supergame.utils.Mappers;

public class SkillTargetedSystem extends IteratingSystem {
    private ComponentMapper<SkillTargetedComponent> stm = Mappers.skillTargeted;
    private ComponentMapper<HealthComponent> hm = Mappers.health;


    public SkillTargetedSystem() {
        super(Family.all(SkillTargetedComponent.class, HealthComponent.class).get());
    }

    @Override
    protected void processEntity(Entity target, float deltaTime) {
        SkillTargetedComponent skillTargetedComponent = stm.get(target);
        HealthComponent healthComponent = hm.get(target);
        BasicSkill basicSkill = skillTargetedComponent.basicSkill;
        float baseDamage = basicSkill.getBaseDamage();

        healthComponent.currentHealth -= baseDamage;

        target.remove(SkillTargetedComponent.class);
    }
}
