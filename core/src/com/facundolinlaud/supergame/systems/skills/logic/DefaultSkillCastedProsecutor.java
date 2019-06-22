package com.facundolinlaud.supergame.systems.skills.logic;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.skills.SkillTargetedComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.model.skill.AreaOfEffect;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck.AreaOfEffectCheckStrategy;
import com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck.CircleAreaOfEffectCheckStrategyStrategyImpl;
import com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck.SquareAreaOfEffectCheckStrategyStrategyImpl;
import com.facundolinlaud.supergame.strategies.skills.epicenter.SkillEpicenterStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

public class DefaultSkillCastedProsecutor extends BaseSkillCastedProsecutor {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private SkillEpicenterStrategy epicenterStrategy;

    public DefaultSkillCastedProsecutor(Engine engine, SkillEpicenterStrategy epicenterStrategy, ParticleFactory particleFactory, LightsManager lightsManager) {
        super(engine, lightsManager, particleFactory);
        this.epicenterStrategy = epicenterStrategy;
    }

    public void execute(Entity caster, Skill skill) {
        Vector2 epicenter = epicenterStrategy.calculate(caster);
        affectSurroundingEntities(caster, skill, epicenter);
        createParticleEffect(skill, epicenter);
        createLightEffect(skill, epicenter);
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
}
