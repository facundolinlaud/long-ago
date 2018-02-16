package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.model.skill.*;

import java.util.Map;

public class AvailableSkillsFactory {
    private Map<Integer, MeleeSkill> meleeSkills;
    private Map<Integer, RangedSkill> rangedSkills;
    private Map<Integer, SpellSkill> spellSkills;

    public AvailableSkillsFactory() {
        SkillsModel skillsModel = ModelFactory.getAvailableSkillsModel();

        meleeSkills = skillsModel.getMeleeSkills();
        rangedSkills = skillsModel.getRangedSkills();
        spellSkills = skillsModel.getSpellSkills();
    }

    public SkillType getSkillTypeById(int id){
        if(meleeSkills.containsKey(id))
            return SkillType.MELEE_SKILL;
        else if(rangedSkills.containsKey(id))
            return SkillType.RANGED_SKILL;
        else if(spellSkills.containsKey(id))
            return SkillType.SPELL_SKILL;
        else
            throw new RuntimeException();
    }

    public MeleeSkill getMeleeSkill(int id){
        return meleeSkills.get(id);
    }

    public RangedSkill getRangedSkill(int id){
        return rangedSkills.get(id);
    }

    public SpellSkill getSpellSkill(int id){
        return spellSkills.get(id);
    }
}
