package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.facundolinlaud.supergame.builder.ParticleBuilder;
import com.facundolinlaud.supergame.managers.world.ParticleManager;

public class ParticleFactory {
    private ParticleManager particleManager;

    public ParticleFactory(ParticleManager particleManager) {
        this.particleManager = particleManager;
    }

    public PooledEffect getEffect(String particleId) {
        PooledEffect effect = particleManager.getPooledParticleEffect(particleId);
        effect.start();

        return effect;
    }

    public ParticleBuilder create(String particleId, Entity entity) {
        PooledEffect effect = getEffect(particleId);
        return new ParticleBuilder(effect, entity);
    }

    public ParticleBuilder create(String particleId) {
        PooledEffect effect = getEffect(particleId);
        return new ParticleBuilder(effect);
    }
}
