package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.skills.castingrequest.KeyPressThenClickCastingRequestStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

public class KeyPressThenClickCastingRequestSystem extends IteratingSystem {
    private ComponentMapper<SkillCastingRequestComponent> scrm = Mappers.skillCastingRequest;
    private ComponentMapper<SkillClickComponent> scm = Mappers.skillClick;

    private KeyPressThenClickCastingRequestStrategy requestStrategy;

    public KeyPressThenClickCastingRequestSystem() {
        super(Family.all(SkillCastingRequestComponent.class, SkillClickComponent.class).get());
        this.requestStrategy = new KeyPressThenClickCastingRequestStrategy();
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillCastingRequestComponent requestComponent = scrm.get(caster);
        SkillClickComponent clickComponent = scm.get(caster);

        Skill skill = requestComponent.getRequestedSkill();

        if(justLeftClicked()){
            System.out.println(justLeftClicked());
            clickComponent.registerClick();
            this.requestStrategy.attemptToCast(caster, skill);
        }
    }

    private boolean justLeftClicked(){
        return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
    }
}
