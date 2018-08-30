package com.facundolinlaud.supergame.model.skill;

public class ProjectileInformation {
    private float maxTravelDistance;
    private String particlePath;
    private boolean onlyParticle;
    private EffectOnEnemy effectOnEnemy;
    private float projectileSpeed;

    public ProjectileInformation() {}

    public float getMaxTravelDistance() {
        return maxTravelDistance;
    }

    public void setMaxTravelDistance(float maxTravelDistance) {
        this.maxTravelDistance = maxTravelDistance;
    }

    public String getParticlePath() {
        return particlePath;
    }

    public void setParticlePath(String particlePath) {
        this.particlePath = particlePath;
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
}
