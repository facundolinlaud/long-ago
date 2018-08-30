package com.facundolinlaud.supergame.factory;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.facundolinlaud.supergame.managers.world.ParticleManager;
import com.facundolinlaud.supergame.model.particle.ParticleType;

public class ParticleFactory {
    private ParticleManager particleManager;

    public ParticleFactory(ParticleManager particleManager) {
        this.particleManager = particleManager;
    }

    public PooledEffect create(ParticleType type){
        PooledEffect effect = particleManager.getPooledParticleEffect(type);
        effect.start();

        return effect;
    }
}
