package com.facundolinlaud.supergame.model.skill;

import com.facundolinlaud.supergame.model.equip.EquipType;
import com.facundolinlaud.supergame.model.status.Action;

public abstract class BasicSkill {
    private String name;
    private String picturePath;

    private int baseDamage;
    private float coolDown;
    private float castTime;
    private float lockdownTime;

    private boolean interruptible;

    private EquipType equipmentRequired;
    private DamageType skillDamageType;
    private Action castingAction;
    private Action executingAction;
    private AnimationFlow animationFlow;
    private AreaOfEffect areaOfEffect;
    private int areaOfEffectSize;
    private SkillType skillType;

    public BasicSkill() {}

    public BasicSkill(String name, String picturePath, int baseDamage, float coolDown, float castTime, float lockdownTime, boolean interruptible, EquipType equipmentRequired, DamageType skillDamageType, Action castingAction, Action executingAction, AnimationFlow animationFlow, AreaOfEffect areaOfEffect, int areaOfEffectSize, SkillType skillType) {
        this.name = name;
        this.picturePath = picturePath;
        this.baseDamage = baseDamage;
        this.coolDown = coolDown;
        this.castTime = castTime;
        this.lockdownTime = lockdownTime;
        this.interruptible = interruptible;
        this.equipmentRequired = equipmentRequired;
        this.skillDamageType = skillDamageType;
        this.castingAction = castingAction;
        this.executingAction = executingAction;
        this.animationFlow = animationFlow;
        this.areaOfEffect = areaOfEffect;
        this.areaOfEffectSize = areaOfEffectSize;
        this.skillType = skillType;
    }

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

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public float getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
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

    public DamageType getSkillDamageType() {
        return skillDamageType;
    }

    public void setSkillDamageType(DamageType skillDamageType) {
        this.skillDamageType = skillDamageType;
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

    public int getAreaOfEffectSize() {
        return areaOfEffectSize;
    }

    public void setAreaOfEffectSize(int areaOfEffectSize) {
        this.areaOfEffectSize = areaOfEffectSize;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }
}
