package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.SkillLockdownComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.utils.Mappers;

public class SkillLockdownSystem extends IteratingSystem {
    private ComponentMapper<SkillLockdownComponent> slm = Mappers.skillLockdown;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    public SkillLockdownSystem() {
        super(Family.all(SkillLockdownComponent.class).get());
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillLockdownComponent lockdown = slm.get(caster);

        if(lockdown.isOver()){
            StatusComponent statusComponent = sm.get(caster);
            statusComponent.setAction(Action.STANDING);
            caster.remove(SkillLockdownComponent.class);
        } else lockdown.tick(deltaTime);
    }
}
