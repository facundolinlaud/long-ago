package com.facundolinlaud.supergame.model.skill;

import com.facundolinlaud.supergame.model.equip.EquipType;
import com.facundolinlaud.supergame.model.status.Action;

public class MeleeSkill extends BasicSkill {
    public MeleeSkill(String name, String picturePath, int baseDamage, float coolDown, float castTime, boolean interruptable, EquipType equipmentRequired, DamageType skillDamageType, Action castingAnimation, AnimationFlow animationFlow, SkillTrigger skillTrigger, AreaOfEffect areaOfEffect, SkillType skillType) {
        super(name, picturePath, baseDamage, coolDown, castTime, interruptable, equipmentRequired, skillDamageType, castingAnimation, animationFlow, skillTrigger, areaOfEffect, skillType);
    }
}
