package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.builder.ProjectileBuilder;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.model.particle.ParticleType;

public class ProjectilesService extends Service {
    private ParticleFactory particleFactory;

    public ProjectilesService(Engine engine, ParticleFactory particleFactory) {
        super(engine);
        this.particleFactory = particleFactory;
    }

    public void create(ProjectileBuilder projectileBuilder, ParticleType particleType) {
        Entity projectile = projectileBuilder
                .withParticles(particleFactory.getEffect(particleType))
                .build();

        getEngine().addEntity(projectile);
    }
}
