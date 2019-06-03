package com.facundolinlaud.supergame.model.skill;

import com.facundolinlaud.supergame.model.particle.ParticleType;

public class ProjectileInformation {
    private float maxTravelDistance;
    private ParticleType particleType;
    private boolean onlyParticle;
    private EffectOnEnemy effectOnEnemy;
    private float projectileSpeed;
    private String projectileTexture;

    public ProjectileInformation() {}

    public float getMaxTravelDistance() {
        return maxTravelDistance;
    }

    public void setMaxTravelDistance(float maxTravelDistance) {
        this.maxTravelDistance = maxTravelDistance;
    }

    public ParticleType getParticleType() {
        return particleType;
    }

    public void setParticleType(ParticleType particleType) {
        this.particleType = particleType;
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

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setProjectileSpeed(float projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    public String getProjectileTexture() {
        return projectileTexture;
    }

    public void setProjectileTexture(String projectileTexture) {
        this.projectileTexture = projectileTexture;
    }
}
