package com.facundolinlaud.supergame.model.skill;

import com.facundolinlaud.supergame.model.particle.ParticleType;

public class ProjectileInformation {
    private float maxTravelDistance;
    private ParticleType particle;
    private boolean onlyParticle;
    private EffectOnEnemy effectOnEnemy;
    private float speed;
    private String texture;

    public ProjectileInformation() {}

    public float getMaxTravelDistance() {
        return maxTravelDistance;
    }

    public void setMaxTravelDistance(float maxTravelDistance) {
        this.maxTravelDistance = maxTravelDistance;
    }

    public ParticleType getParticle() {
        return particle;
    }

    public void setParticle(ParticleType particle) {
        this.particle = particle;
    }

    public boolean isOnlyParticle() {
        return onlyParticle;
    }

    public void setOnlyParticle(boolean onlyParticle) {
        this.onlyParticle = onlyParticle;
    }

    public EffectOnEnemy getEffectOnEnemy() {
        return effectOnEnemy;
    }

    public void setEffectOnEnemy(EffectOnEnemy effectOnEnemy) {
        this.effectOnEnemy = effectOnEnemy;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float projectileSpeed) {
        this.speed = projectileSpeed;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }
}
