package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.builder.ProjectileBuilder;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.ParticleComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.ProjectileComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.utils.Mappers;

public class ProjectilesService extends Service {
    private ComponentMapper<ParticleComponent> pam = Mappers.particle;

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

    public void destroy(Entity projectile) {
        if (pam.has(projectile)) {
            ParticleComponent particleComponent = pam.get(projectile);
            particleComponent.setEntityJustParticle(true);

            projectile.remove(ProjectileComponent.class);
            projectile.remove(BodyComponent.class);
            projectile.remove(RenderComponent.class);
        } else {
            getEngine().removeEntity(projectile);
        }
    }
}
