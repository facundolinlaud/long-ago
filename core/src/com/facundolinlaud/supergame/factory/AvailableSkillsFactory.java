package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.model.skill.*;

import java.util.Map;

public class AvailableSkillsFactory {
    private Map<Integer, MeleeSkill> meleeSkills;
    private Map<Integer, RangedSkill> rangedSkills;

    public AvailableSkillsFactory() {
        SkillsModel skillsModel = ModelFactory.getAvailableSkillsModel();

        meleeSkills = skillsModel.getMeleeSkills();
        rangedSkills = skillsModel.getRangedSkills();
    }

    public SkillType getSkillTypeById(int id){
        if(meleeSkills.containsKey(id))
            return SkillType.MELEE_SKILL;
        else if(rangedSkills.containsKey(id))
            return SkillType.RANGED_SKILL;
        else
            throw new RuntimeException();
    }

    public MeleeSkill getMeleeSkill(int id){
        return meleeSkills.get(id);
    }

    public RangedSkill getRangedSkill(int id){
        return rangedSkills.get(id);
    }

    public BasicSkill getBasicSkill(int id, SkillType skillType) {
        switch(skillType){
            case MELEE_SKILL:
                return getMeleeSkill(id);
            case RANGED_SKILL:
                return getRangedSkill(id);
            default:
                return null;
        }
    }
}
