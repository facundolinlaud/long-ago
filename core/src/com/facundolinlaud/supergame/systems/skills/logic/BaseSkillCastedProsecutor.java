package com.facundolinlaud.supergame.systems.skills.logic;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.utils.Mappers;

public abstract class BaseSkillCastedProsecutor {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

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

    protected void createAreaParticleEffect(Skill skill, Vector2 epicenter) {
        if(!skill.hasAreaParticleEffect())
            return;

        ParticleType particleType = skill.getAreaParticle();
        Entity particle = particleFactory
                .create(particleType)
                .at(epicenter)
                .build();

        engine.addEntity(particle);
    }

    protected void createHitParticleEffect(Entity victim, Skill skill) {
        if(!skill.hasHitParticleEffect())
            return;

        ParticleType particleType = skill.getHitParticle();
        particleFactory.create(particleType, victim).build();
    }

    protected void shakeScreen(Skill skill, Vector2 epicenter) {
        if(skill.hasScreenShake()) {
            float power = skill.getScreenShake().getPower();
            float duration = skill.getScreenShake().getDuration();
            this.cameraManager.shake(power, duration, epicenter);
        }
    }
}
