package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ParticleComponent implements Component, Poolable {
    private PooledEffect effect;
    private boolean isEntityJustParticle;

    public ParticleComponent(PooledEffect effect, boolean isEntityJustParticle) {
        this.effect = effect;
        this.isEntityJustParticle = isEntityJustParticle;
    }

    public PooledEffect getEffect() {
        return this.effect;
    }

    public boolean isEntityJustParticle() {
        return isEntityJustParticle;
    }

    @Override
    public void reset() {
        effect.free();
        effect = null;
    }
}
