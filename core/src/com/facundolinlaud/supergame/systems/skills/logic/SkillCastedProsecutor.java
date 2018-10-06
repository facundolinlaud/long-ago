package com.facundolinlaud.supergame.systems.skills.logic;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.ParticleComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.skills.SkillTargetedComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.model.light.LightModel;
import com.facundolinlaud.supergame.model.particle.Particle;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.model.skill.AreaOfEffect;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck.AreaOfEffectCheckStrategy;
import com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck.CircleAreaOfEffectCheckStrategyStrategyImpl;
import com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck.SquareAreaOfEffectCheckStrategyStrategyImpl;
import com.facundolinlaud.supergame.strategies.skills.epicenter.SkillEpicenterStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

public class SkillCastedProsecutor {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private Engine engine;
    private SkillEpicenterStrategy epicenterStrategy;
    private ParticleFactory particleFactory;
    private LightsManager lightsManager;

    public SkillCastedProsecutor(Engine engine, SkillEpicenterStrategy epicenterStrategy, ParticleFactory particleFactory, LightsManager lightsManager) {
        this.epicenterStrategy = epicenterStrategy;
        this.particleFactory = particleFactory;
        this.lightsManager = lightsManager;
        this.engine = engine;
    }

    public void execute(Entity caster, Skill skill) {
        Vector2 epicenter = this.epicenterStrategy.calculate(caster);
        affectSurroundingEntities(caster, skill, epicenter);

        if(skill.hasParticleEffect())
            createParticleEffect(skill.getParticleType(), epicenter);

        if(skill.hasLightEffect())
            createLightEffect(skill.getLightModel(), epicenter);

    }

    private void createLightEffect(LightModel model, Vector2 epicenter) {
        lightsManager.create(model, epicenter.x, epicenter.y);
    }

    private void affectSurroundingEntities(Entity caster, Skill skill, Vector2 epicenter) {
        ImmutableArray<Entity> victims = engine.getEntitiesFor(Family.all(
                HealthComponent.class,
                PositionComponent.class).get());

        AreaOfEffectCheckStrategy aoeCheck = buildAreaOfEffectChecker(
                skill.getAreaOfEffect(),
                skill.getAreaOfEffectSize(),
                epicenter);

        for(Entity victim : victims){
            if(victim != caster){
                PositionComponent victimPosition = pm.get(victim);

                if(aoeCheck.isInArea(victimPosition.x, victimPosition.y)){
                    applyEffectsToVictim(caster, victim, skill);
                }
            }
        }
    }

    // TODO: there's a builtin libgdx feature for this
    private AreaOfEffectCheckStrategy buildAreaOfEffectChecker(AreaOfEffect aoe, float aoeSize, Vector2 pos){
        switch(aoe){
            case CIRCLE:
                return new CircleAreaOfEffectCheckStrategyStrategyImpl(pos, aoeSize);
            default:
                return new SquareAreaOfEffectCheckStrategyStrategyImpl(pos, aoeSize);
        }
    }

    private void applyEffectsToVictim(Entity caster, Entity victim, Skill skill) {
        victim.add(new SkillTargetedComponent(caster, skill));
    }

    private void createParticleEffect(ParticleType particleType, Vector2 epicenter) {
        PooledEffect pooledEffect = particleFactory.create(particleType);

        Entity particle = new Entity();
        particle.add(new PositionComponent(epicenter));
        particle.add(new ParticleComponent(pooledEffect, true));

        engine.addEntity(particle);
    }
}
