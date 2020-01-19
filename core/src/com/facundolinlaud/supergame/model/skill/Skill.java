package com.facundolinlaud.supergame.model.skill;

import com.facundolinlaud.supergame.model.equip.EquipType;
import com.facundolinlaud.supergame.model.light.LightModel;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.model.status.Action;

public class Skill {
    private String name;
    private String picturePath;
    private String disabledPicturePath;
    private SkillType skillType;
    private DamageType skillDamageType;
    private float manaConsumption;
    private String description;

    /* Restrictions */
    private float cooldown;
    private float castTime;
    private float lockdownTime;
    private boolean interruptible;
    private EquipType equipmentRequired;

    /* Visuals */
    private Action castingAction;
    private Action executingAction;
    private AnimationFlow animationFlow;

    /* Areas and effects */
    private AreaOfEffect areaOfEffect;
    private float areaOfEffectSize;
    private float baseDamage;
    private float baseHeal;

    /* Projectile or two-click-skill based information */
    private ProjectileInformation projectileInformation;
    private TwoClickInformation twoClickInformation;

    /* Particles */
    private ParticleType areaParticle;
    private ParticleType hitParticle;

    /* Lights */
    private boolean hasLightEffect;
    private LightModel lightModel;

    /* Screen Shake */
    private ScreenShake screenShake;

    public void setHasLightEffect(boolean hasLightEffect) {
        this.hasLightEffect = hasLightEffect;
    }

    public boolean hasLightEffect() {
        return hasLightEffect;
    }

    public LightModel getLightModel() {
        return lightModel;
    }

    public void setLightModel(LightModel lightModel) {
        this.lightModel = lightModel;
    }

    public void setTwoClickInformation(TwoClickInformation twoClickInformation) {
        this.twoClickInformation = twoClickInformation;
    }

    public Skill() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getDisabledPicturePath() {
        return disabledPicturePath;
    }

    public void setDisabledPicturePath(String disabledPicturePath) {
        this.disabledPicturePath = disabledPicturePath;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    public DamageType getSkillDamageType() {
        return skillDamageType;
    }

    public void setSkillDamageType(DamageType skillDamageType) {
        this.skillDamageType = skillDamageType;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public float getCastTime() {
        return castTime;
    }

    public void setCastTime(float castTime) {
        this.castTime = castTime;
    }

    public float getLockdownTime() {
        return lockdownTime;
    }

    public void setLockdownTime(float lockdownTime) {
        this.lockdownTime = lockdownTime;
    }

    public boolean isInterruptible() {
        return interruptible;
    }

    public void setInterruptible(boolean interruptible) {
        this.interruptible = interruptible;
    }

    public EquipType getEquipmentRequired() {
        return equipmentRequired;
    }

    public void setEquipmentRequired(EquipType equipmentRequired) {
        this.equipmentRequired = equipmentRequired;
    }

    public Action getCastingAction() {
        return castingAction;
    }

    public void setCastingAction(Action castingAction) {
        this.castingAction = castingAction;
    }

    public Action getExecutingAction() {
        return executingAction;
    }

    public void setExecutingAction(Action executingAction) {
        this.executingAction = executingAction;
    }

    public AnimationFlow getAnimationFlow() {
        return animationFlow;
    }

    public void setAnimationFlow(AnimationFlow animationFlow) {
        this.animationFlow = animationFlow;
    }

    public AreaOfEffect getAreaOfEffect() {
        return areaOfEffect;
    }

    public void setAreaOfEffect(AreaOfEffect areaOfEffect) {
        this.areaOfEffect = areaOfEffect;
    }

    public float getAreaOfEffectSize() {
        return areaOfEffectSize;
    }

    public void setAreaOfEffectSize(float areaOfEffectSize) {
        this.areaOfEffectSize = areaOfEffectSize;
    }

    public float getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(float baseDamage) {
        this.baseDamage = baseDamage;
    }

    public float getBaseHeal() {
        return baseHeal;
    }

    public void setBaseHeal(float baseHeal) {
        this.baseHeal = baseHeal;
    }

    public ProjectileInformation getProjectileInformation() {
        return projectileInformation;
    }

    public void setProjectileInformation(ProjectileInformation projectileInformation) {
        this.projectileInformation = projectileInformation;
    }

    public boolean isProjectile() {
        return this.skillType == SkillType.PROJECTILE;
    }

    public boolean hasAreaParticleEffect(){
        return this.areaParticle != ParticleType.NONE;
    }

    public ParticleType getAreaParticle() {
        return areaParticle;
    }

    public void setAreaParticle(ParticleType areaParticle) {
        this.areaParticle = areaParticle;
    }

    public TwoClickInformation getTwoClickInformation() {
        return twoClickInformation;
    }

    public ScreenShake getScreenShake() {
        return screenShake;
    }

    public void setScreenShake(ScreenShake screenShake) {
        this.screenShake = screenShake;
    }

    public boolean hasScreenShake(){
        return this.screenShake != null;
    }

    public float getManaConsumption() {
        return manaConsumption;
    }

    public void setManaConsumption(float manaConsumption) {
        this.manaConsumption = manaConsumption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParticleType getHitParticle() {
        return hitParticle;
    }

    public void setHitParticle(ParticleType hitParticle) {
        this.hitParticle = hitParticle;
    }

    public boolean hasHitParticleEffect(){
        return this.hitParticle != ParticleType.NONE;
    }
}
