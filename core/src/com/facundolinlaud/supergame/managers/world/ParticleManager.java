package com.facundolinlaud.supergame.managers.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.facundolinlaud.supergame.model.particle.ParticleType;

import java.util.HashMap;
import java.util.Map;

public class ParticleManager {
    private static final float DEFAULT_SCALE = 1.0f;
    private static final int DEFAULT_START_CAPACITY = 5;
    private static final int DEFAULT_MAX_CAPACITY = 20;

    public static final String PARTICLES_PATH = "particles/png";
    public static final String FIRE_PARTICLE_PATH = "particles/particle.p";

    private Map<ParticleType, ParticleEffect> particleEffects;
    private Map<ParticleType, ParticleEffectPool> particleEffectPool;
    private FileHandle particlesPath;

    public ParticleManager() {
        this.particleEffects = new HashMap<>();
        this.particleEffectPool = new HashMap<>();
        this.particlesPath = Gdx.files.internal(PARTICLES_PATH);

        initializePools();
    }

    private void initializePools() {
        ParticleEffect fire = new ParticleEffect();
        fire.load(Gdx.files.internal(FIRE_PARTICLE_PATH), particlesPath);
        fire.setPosition(20, 50);
        addParticleEffect(ParticleType.FIRE, fire);
    }

    public void addParticleEffect(ParticleType type, ParticleEffect effect){
        addParticleEffect(type, effect, DEFAULT_SCALE, DEFAULT_START_CAPACITY, DEFAULT_MAX_CAPACITY);
    }

    public void addParticleEffect(ParticleType type, ParticleEffect effect, float scale, int startCapacity, int maxCapacity){
        effect.scaleEffect(scale);
        particleEffects.put(type, effect);
        particleEffectPool.put(type, new ParticleEffectPool(effect, startCapacity, maxCapacity));

    }

    public PooledEffect getPooledParticleEffect(ParticleType type){
        return particleEffectPool.get(type).obtain();
    }
}
