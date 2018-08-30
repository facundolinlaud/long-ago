package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;

public class ParticleComponent implements Component {
    private PooledEffect effect;
    private boolean loop;
    private float duration;

    public ParticleComponent(PooledEffect effect) {
        this.effect = effect;
        this.loop = true;
        this.duration = -1;
    }

    public ParticleComponent(PooledEffect effect, boolean loop, float duration) {
        this.effect = effect;
        this.loop = loop;
        this.duration = duration;
    }

    public PooledEffect getEffect() {
        return this.effect;
    }

    public boolean isLoop() {
        return this.loop;
    }

    public float getDuration() {
        return this.duration;
    }
}
