package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.facundolinlaud.supergame.builder.ParticleBuilder;
import com.facundolinlaud.supergame.managers.world.ParticleManager;
import com.facundolinlaud.supergame.model.particle.ParticleType;

public class ParticleFactory {
    private ParticleManager particleManager;

    public ParticleFactory(ParticleManager particleManager) {
        this.particleManager = particleManager;
    }

    public PooledEffect getEffect(ParticleType type){
        PooledEffect effect = particleManager.getPooledParticleEffect(type);
        effect.start();

        return effect;
    }

    public ParticleBuilder create(ParticleType particleType, Entity entity){
        PooledEffect effect = getEffect(particleType);
        return new ParticleBuilder(effect, entity);
    }

    public ParticleBuilder create(ParticleType particleType){
        PooledEffect effect = getEffect(particleType);
        return new ParticleBuilder(effect);
    }
}
