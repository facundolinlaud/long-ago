package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.SkillsComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.components.skills.SkillLockDownComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillType;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.strategies.skills.casting.NormalSkillCastingStrategy;
import com.facundolinlaud.supergame.strategies.skills.casting.ProjectileSkillCastingStrategy;
import com.facundolinlaud.supergame.strategies.skills.casting.SkillCastingStrategy;
import com.facundolinlaud.supergame.strategies.skills.casting.SpellSkillCastingStrategy;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.Messages;
import com.facundolinlaud.supergame.utils.events.SkillCastedEvent;

import java.util.HashMap;
import java.util.Map;

public class SkillCastingSystem extends IteratingSystem {
    private ComponentMapper<SkillCastingComponent> scm = Mappers.skillCasting;
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<SkillsComponent> ssm = Mappers.skills;

    private Map<SkillType, SkillCastingStrategy> castingStrategies;
    private MessageDispatcher messageDispatcher;

    public SkillCastingSystem(Engine engine, ParticleFactory particleFactory,
                              LightsManager lightsManager, CameraManager cameraManager) {
        super(Family.all(PositionComponent.class, StatusComponent.class, SkillCastingComponent.class).get());

        this.castingStrategies = new HashMap<>();
        this.castingStrategies.put(SkillType.NORMAL,
                new NormalSkillCastingStrategy(engine, particleFactory, lightsManager, cameraManager));

        this.castingStrategies.put(SkillType.SPELL,
                new SpellSkillCastingStrategy(engine, particleFactory, lightsManager, cameraManager));

        this.castingStrategies.put(SkillType.PROJECTILE,
                new ProjectileSkillCastingStrategy(engine, particleFactory));

        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillCastingComponent skillCastingComponent = scm.get(caster);
        Skill skill = skillCastingComponent.skill;

        if(skillCastingComponent.hasCastingTimeEnded()){
            setExecutingActionToCaster(caster, skill);
            executeSkill(caster, skill);
            caster.remove(SkillCastingComponent.class);
            startSkillCoolDown(caster, skill);

            float lockDownTime = skill.getLockdownTime();
            putCasterOnSkillLockDown(caster, lockDownTime);
        }else if(skillCastingComponent.hasJustStarted())
            setCastingActionToCaster(caster, skill);

        skillCastingComponent.tick(deltaTime);
    }

    private void setCastingActionToCaster(Entity caster, Skill skill) {
        StatusComponent casterStatus = sm.get(caster);
        Action castingAction = skill.getCastingAction();
        casterStatus.setAction(castingAction);
    }

    private void setExecutingActionToCaster(Entity caster, Skill skill){
        StatusComponent casterStatus = sm.get(caster);
        Action executingAction = skill.getExecutingAction();
        casterStatus.setAction(executingAction);
    }

    private void executeSkill(Entity caster, Skill skill){
        executeSkillEffects(caster, skill);
        caster.remove(SkillCastingComponent.class);
    }

    private void startSkillCoolDown(Entity caster, Skill skill) {
        SkillsComponent skillsComponent = ssm.get(caster);
        skillsComponent.startCoolDown(skill);
    }

    private void executeSkillEffects(Entity caster, Skill skill){
        SkillType skillType = skill.getSkillType();

        this.castingStrategies.get(skillType).executeSkillEffects(caster, skill);
        this.messageDispatcher.dispatchMessage(Messages.SKILL_CASTED, new SkillCastedEvent(caster, skill));
    }

    private void putCasterOnSkillLockDown(Entity caster, float lockDownTime){
        caster.add(new SkillLockDownComponent(lockDownTime));
    }
}