package com.facundolinlaud.supergame.model.skill;

import com.facundolinlaud.supergame.model.equip.EquipType;
import com.facundolinlaud.supergame.model.status.Action;

public class RangedSkill extends SpellSkill {
    private float projectileSpeed;

    public RangedSkill() {}

    public RangedSkill(String name, String picturePath, int baseDamage, float coolDown, float castTime, boolean interruptible, EquipType equipmentRequired, DamageType skillDamageType, Action castingAction, Action executingAction, AnimationFlow animationFlow, AreaOfEffect areaOfEffect, int areaOfEffectSize, SkillType skillType, float maxTravelDistance, String particlePath, boolean onlyParticle, EffectOnEnemy effectOnEnemy, SkillTrigger skillTrigger, float projectileSpeed) {
        super(name, picturePath, baseDamage, coolDown, castTime, interruptible, equipmentRequired, skillDamageType, castingAction, executingAction, animationFlow, areaOfEffect, areaOfEffectSize, skillType, maxTravelDistance, particlePath, onlyParticle, effectOnEnemy, skillTrigger);
        this.projectileSpeed = projectileSpeed;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setProjectileSpeed(float projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }
}
