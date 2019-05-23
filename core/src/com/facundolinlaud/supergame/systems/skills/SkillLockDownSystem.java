package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.SkillLockDownComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.utils.Mappers;

public class SkillLockDownSystem extends IteratingSystem {
    private ComponentMapper<SkillLockDownComponent> slm = Mappers.skillLockDown;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    public SkillLockDownSystem() {
        super(Family.all(SkillLockDownComponent.class).get());
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillLockDownComponent lockDown = slm.get(caster);

        if(lockDown.isOver()){
            StatusComponent statusComponent = sm.get(caster);
            statusComponent.setAction(Action.STANDING);
            caster.remove(SkillLockDownComponent.class);
        } else lockDown.tick(deltaTime);
    }
}
