package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.components.skills.SkillLockdownComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillType;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.strategies.skills.casting.NormalSkillCastingStrategy;
import com.facundolinlaud.supergame.strategies.skills.casting.ProjectileSkillCastingStrategy;
import com.facundolinlaud.supergame.strategies.skills.casting.SkillCastingStrategy;
import com.facundolinlaud.supergame.strategies.skills.casting.SpellSkillCastingStrategy;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.Messages;

import java.util.HashMap;
import java.util.Map;

public class SkillCastingSystem extends IteratingSystem {
    private ComponentMapper<SkillCastingComponent> msccm = Mappers.meleeSkillCasting;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private Map<SkillType, SkillCastingStrategy> castingStrategies;
    private MessageDispatcher messageDispatcher;

    public SkillCastingSystem(Engine engine, ParticleFactory particleFactory) {
        super(Family.all(PositionComponent.class, StatusComponent.class, SkillCastingComponent.class).get());

        this.castingStrategies = new HashMap<>();
        this.castingStrategies.put(SkillType.NORMAL, new NormalSkillCastingStrategy(engine, particleFactory));
        this.castingStrategies.put(SkillType.SPELL, new SpellSkillCastingStrategy(engine, particleFactory));
        this.castingStrategies.put(SkillType.PROJECTILE, new ProjectileSkillCastingStrategy());

        this.messageDispatcher = MessageManager.getInstance();
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

    private void setExecutingActionToCaster(Entity caster, SkillCastingComponent skillCastingComponent){
        StatusComponent casterStatus = sm.get(caster);
        Action executingAction = skillCastingComponent.skill.getExecutingAction();
        casterStatus.setAction(executingAction);
    }

    private void executeSkill(Entity caster, SkillCastingComponent skillCastingComponent){
        executeSkillEffects(caster, skillCastingComponent);
        caster.remove(SkillCastingComponent.class);
    }

    private void executeSkillEffects(Entity caster, SkillCastingComponent skillCastingComponent){
        Skill skill = skillCastingComponent.skill;
        SkillType skillType = skill.getSkillType();

        this.castingStrategies.get(skillType).executeSkillEffects(caster, skill);
        this.messageDispatcher.dispatchMessage(Messages.SKILL_CASTED, skill);
    }

    private void putCasterOnSkillLockdown(Entity caster, float lockdownTime){
        caster.add(new SkillLockdownComponent(lockdownTime));
    }
}