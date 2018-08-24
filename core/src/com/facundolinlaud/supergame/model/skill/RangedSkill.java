package com.facundolinlaud.supergame.model.skill;

import com.facundolinlaud.supergame.model.equip.EquipType;
import com.facundolinlaud.supergame.model.status.Action;

public class RangedSkill extends BasicSkill {
    private float maxTravelDistance;
    private String particlePath;
    private boolean onlyParticle;
    private EffectOnEnemy effectOnEnemy;
    private SkillTrigger skillTrigger;
    private float projectileSpeed;
    private boolean isProjectile;

    public RangedSkill(){}

    public RangedSkill(String name, String picturePath, int baseDamage, float coolDown, float castTime, float lockdownTime, boolean interruptible, EquipType equipmentRequired, DamageType skillDamageType, Action castingAction, Action executingAction, AnimationFlow animationFlow, AreaOfEffect areaOfEffect, int areaOfEffectSize, SkillType skillType, float maxTravelDistance, String particlePath, boolean onlyParticle, EffectOnEnemy effectOnEnemy, SkillTrigger skillTrigger, float projectileSpeed, boolean isProjectile) {
        super(name, picturePath, baseDamage, coolDown, castTime, lockdownTime, interruptible, equipmentRequired, skillDamageType, castingAction, executingAction, animationFlow, areaOfEffect, areaOfEffectSize, skillType);
        this.maxTravelDistance = maxTravelDistance;
        this.particlePath = particlePath;
        this.onlyParticle = onlyParticle;
        this.effectOnEnemy = effectOnEnemy;
        this.skillTrigger = skillTrigger;
        this.projectileSpeed = projectileSpeed;
        this.isProjectile = isProjectile;
    }

    public float getMaxTravelDistance() {
        return maxTravelDistance;
    }

    public String getParticlePath() {
        return particlePath;
    }

    public boolean isOnlyParticle() {
        return onlyParticle;
    }

    public EffectOnEnemy getEffectOnEnemy() {
        return effectOnEnemy;
    }

    public SkillTrigger getSkillTrigger() {
        return skillTrigger;
    }

    public void setMaxTravelDistance(float maxTravelDistance) {
        this.maxTravelDistance = maxTravelDistance;
    }

    public void setParticlePath(String particlePath) {
        this.particlePath = particlePath;
    }

    public void setOnlyParticle(boolean onlyParticle) {
        this.onlyParticle = onlyParticle;
    }

    public void setEffectOnEnemy(EffectOnEnemy effectOnEnemy) {
        this.effectOnEnemy = effectOnEnemy;
    }

    public void setSkillTrigger(SkillTrigger skillTrigger) {
        this.skillTrigger = skillTrigger;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setProjectileSpeed(float projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    public boolean isProjectile() {
        return isProjectile;
    }

    public void setProjectile(boolean projectile) {
        isProjectile = projectile;
    }
}