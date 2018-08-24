package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.SkillLockdownComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.model.skill.SkillType;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.strategies.skills.casted.NonProjectileCastedStrategy;
import com.facundolinlaud.supergame.strategies.skills.casted.ProjectileCastedStrategy;
import com.facundolinlaud.supergame.strategies.skills.casted.SkillCastedStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.HashMap;

public class SkillCastingSystem extends IteratingSystem {
    private ComponentMapper<SkillCastingComponent> msccm = Mappers.meleeSkillCasting;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private Engine engine;

    public SkillCastingSystem(Engine engine) {
        super(Family.all(PositionComponent.class, StatusComponent.class, SkillCastingComponent.class).get());
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillCastingComponent skillCastingComponent = msccm.get(caster);

        if(skillCastingComponent.hasCastingTimeEnded()){
            setExecutingActionToCaster(caster, skillCastingComponent);
            executeSkill(caster, skillCastingComponent);
            caster.remove(SkillCastingComponent.class);

            float lockdownTime = skillCastingComponent.skill.getLockdownTime();
            putCasterOnSkillLockdown(caster, lockdownTime);
        }else if(skillCastingComponent.hasJustStarted())
            setCastingActionToCaster(caster, skillCastingComponent);

        skillCastingComponent.tick(deltaTime);
    }

    private void setCastingActionToCaster(Entity caster, SkillCastingComponent skillCastingComponent) {
        StatusComponent casterStatus = sm.get(caster);
        Action castingAction = skillCastingComponent.skill.getCastingAction();
        casterStatus.setAction(castingAction);
    }

    private void executeSkill(Entity caster, SkillCastingComponent skillCastingComponent){
        executeSkillEffects(caster, skillCastingComponent);
        caster.remove(SkillCastingComponent.class);
    }

    private void setExecutingActionToCaster(Entity caster, SkillCastingComponent skillCastingComponent){
        StatusComponent casterStatus = sm.get(caster);
        Action executingAction = skillCastingComponent.skill.getExecutingAction();
        casterStatus.setAction(executingAction);
    }

    private void executeSkillEffects(Entity caster, SkillCastingComponent skillCastingComponent){
        SkillCastedStrategy strategy;

        if(skillCastingComponent.skill.isProjectile()) {
            strategy = new ProjectileCastedStrategy(engine);
        }else{
            strategy = new NonProjectileCastedStrategy(engine);
        }

        strategy.execute(caster, skillCastingComponent.skill);
    }

    private void putCasterOnSkillLockdown(Entity caster, float lockdownTime){
        caster.add(new SkillLockdownComponent(lockdownTime));
    }
}