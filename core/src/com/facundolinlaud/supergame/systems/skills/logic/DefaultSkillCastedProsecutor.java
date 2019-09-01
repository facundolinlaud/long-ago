package com.facundolinlaud.supergame.systems.skills.logic;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.skills.SkillTargetedComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.skills.epicenter.SkillEpicenterStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

public class DefaultSkillCastedProsecutor extends BaseSkillCastedProsecutor {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private SkillEpicenterStrategy epicenterStrategy;

    public DefaultSkillCastedProsecutor(Engine engine, SkillEpicenterStrategy epicenterStrategy,
                                        ParticleFactory particleFactory,
                                        LightsManager lightsManager, CameraManager cameraManager) {
        super(engine, lightsManager, particleFactory, cameraManager);
        this.epicenterStrategy = epicenterStrategy;
    }

    public void execute(Entity caster, Skill skill) {
        Vector2 epicenter = epicenterStrategy.calculate(caster);
        affectSurroundingEntities(caster, skill, epicenter);
        createAreaParticleEffect(skill, epicenter);
        createLightEffect(skill, epicenter);
        shakeScreen(skill, epicenter);
    }

    private void affectSurroundingEntities(Entity caster, Skill skill, Vector2 epicenter) {
        ImmutableArray<Entity> victims = engine.getEntitiesFor(Family.all(
                HealthComponent.class,
                PositionComponent.class).get());

        Circle area = buildAreaOfEffect(epicenter, skill.getAreaOfEffectSize());

        for(Entity victim : victims){
            if(victim != caster){
                PositionComponent victimPosition = pm.get(victim);

                if(area.contains(victimPosition.x, victimPosition.y)){
                    createHitParticleEffect(victim, skill);
                    applyEffectsToVictim(caster, victim, skill);
                }
            }
        }
    }

    private Circle buildAreaOfEffect(Vector2 pos, float radius){
        return new Circle(pos.x, pos.y, radius);
    }

    private void applyEffectsToVictim(Entity caster, Entity victim, Skill skill) {
        victim.add(new SkillTargetedComponent(caster, skill));
    }
}
