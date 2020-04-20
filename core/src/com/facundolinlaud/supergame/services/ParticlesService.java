package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.model.particle.ParticleType;

public class ParticlesService extends Service {
    private ParticleFactory particleFactory;

    public ParticlesService(Engine engine, ParticleFactory particleFactory) {
        super(engine);
        this.particleFactory = particleFactory;
    }

    public void create(ParticleType particleType, Vector2 position) {
        Entity particle = particleFactory
                .create(particleType)
                .at(position)
                .build();

        getEngine().addEntity(particle);
    }
}
