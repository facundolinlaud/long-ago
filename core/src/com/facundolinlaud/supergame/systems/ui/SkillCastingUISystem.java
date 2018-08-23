package com.facundolinlaud.supergame.systems.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.ui.controller.SkillCastingUIController;
import com.facundolinlaud.supergame.utils.Mappers;

public class SkillCastingUISystem extends IteratingSystem {
    private ComponentMapper<SkillCastingComponent> scm = Mappers.skillCasting;

    private SkillCastingUIController castingBarController;

    public SkillCastingUISystem(SkillCastingUIController castingBarController){
        super(Family.all(KeyboardComponent.class, SkillCastingComponent.class).get());
        this.castingBarController = castingBarController;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SkillCastingComponent skill = scm.get(entity);

        String skillName = skill.basicSkill.getName();
        float timeToCast = skill.timeToCast;
        float castTime = skill.basicSkill.getCastTime();

        castingBarController.updateCastingBar(skillName, timeToCast, castTime);
    }
}
