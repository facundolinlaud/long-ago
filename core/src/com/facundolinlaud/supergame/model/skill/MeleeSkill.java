package com.facundolinlaud.supergame.model.skill;

import com.facundolinlaud.supergame.model.equip.EquipType;
import com.facundolinlaud.supergame.model.status.Action;

public class MeleeSkill extends BasicSkill {

    public MeleeSkill() {}

    public MeleeSkill(String name, String picturePath, int baseDamage, float coolDown, float castTime, boolean interruptible, EquipType equipmentRequired, DamageType skillDamageType, Action castingAction, Action executingAction, AnimationFlow animationFlow, AreaOfEffect areaOfEffect, int areaOfEffectSize, SkillType skillType) {
        super(name, picturePath, baseDamage, coolDown, castTime, interruptible, equipmentRequired, skillDamageType, castingAction, executingAction, animationFlow, areaOfEffect, areaOfEffectSize, skillType);
    }
}
