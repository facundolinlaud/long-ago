package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.facundolinlaud.supergame.components.ParticleComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.utils.Mappers;

public class ParticleSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pom = Mappers.position;
    private ComponentMapper<ParticleComponent> pam = Mappers.particle;

    private SpriteBatch batch;

    public ParticleSystem(SpriteBatch batch) {
        super(Family.all(PositionComponent.class, ParticleComponent.class).get());
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ParticleComponent particleComponent = pam.get(entity);
        PositionComponent positionComponent = pom.get(entity);

        PooledEffect effect = particleComponent.getEffect();
        effect.setPosition(positionComponent.x, positionComponent.y);
        effect.draw(batch, deltaTime);
    }
}
