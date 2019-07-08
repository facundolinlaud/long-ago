package com.facundolinlaud.supergame.systems.skills.logic;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.ParticleComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.managers.world.ScreenShakeManager;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.model.skill.Skill;

public abstract class BaseSkillCastedProsecutor {
    protected Engine engine;
    protected LightsManager lightsManager;
    protected ParticleFactory particleFactory;
    protected CameraManager cameraManager;

    public BaseSkillCastedProsecutor(Engine engine, LightsManager lightsManager,
                                     ParticleFactory particleFactory, CameraManager cameraManager) {
        this.engine = engine;
        this.lightsManager = lightsManager;
        this.particleFactory = particleFactory;
        this.cameraManager = cameraManager;
    }

    protected void createLightEffect(Skill skill, Vector2 epicenter) {
        if(!skill.hasLightEffect())
            return;

        lightsManager.create(skill.getLightModel(), epicenter.x, epicenter.y);
    }

    protected void createParticleEffect(Skill skill, Vector2 epicenter) {
        if(!skill.hasParticleEffect())
            return;

        ParticleType particleType = skill.getParticleType();
        ParticleEffectPool.PooledEffect pooledEffect = particleFactory.create(particleType);

        Entity particle = new Entity();
        particle.add(new PositionComponent(epicenter));
        particle.add(new ParticleComponent(pooledEffect, true));

        engine.addEntity(particle);
    }

    protected void shakeScreen(Skill skill, Vector2 epicenter) {
        if(skill.hasScreenShake())
            this.cameraManager.shake(skill.getScreenShake(), epicenter);
    }
}
