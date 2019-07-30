package com.facundolinlaud.supergame.strategies.skills.casting;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.builder.ProjectileBuilder;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.skills.epicenter.RangedSkillEpicenterStrategy;
import com.facundolinlaud.supergame.strategies.skills.epicenter.SkillEpicenterStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

public class ProjectileSkillCastingStrategy implements SkillCastingStrategy {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private Engine engine;
    private SkillEpicenterStrategy epicenterStrategy;
    private ParticleFactory particleFactory;

    public ProjectileSkillCastingStrategy(Engine engine, ParticleFactory particleFactory) {
        this.engine = engine;
        this.particleFactory = particleFactory;
        this.epicenterStrategy = new RangedSkillEpicenterStrategy();
    }

    @Override
    public void executeSkillEffects(Entity caster, Skill skill) {
        PositionComponent positionComponent = pm.get(caster);
        Vector2 origin = positionComponent.getPosition();
        Vector2 destination = epicenterStrategy.calculate(caster);
        Vector2 direction = resolveDirection(origin, destination);

        String texture = skill.getProjectileInformation().getTexture();

        Entity projectile = new ProjectileBuilder(caster, skill, origin)
                .withOrigin(origin, direction)
                .withDirection(direction)
                .withPicture(texture, calculateRotation(direction))
                .withParticles(particleFactory.getEffect(skill.getProjectileInformation().getParticle()))
                .build();

        engine.addEntity(projectile);
    }

    private float calculateRotation(Vector2 direction){
        return direction.angle();
    }

    private Vector2 resolveDirection(Vector2 origin, Vector2 destination){
        Vector2 direction = new Vector2(destination.x - origin.x, destination.y - origin.y);
        return direction.scl(1 / direction.len());
    }
}
