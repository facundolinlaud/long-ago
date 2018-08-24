package com.facundolinlaud.supergame.model.skill;

import java.util.Map;

public class SkillsModel {
    private Map<Integer, MeleeSkill> meleeSkills;
    private Map<Integer, RangedSkill> rangedSkills;

    public SkillsModel(){}

    public SkillsModel(Map<Integer, MeleeSkill> meleeSkills, Map<Integer, RangedSkill> rangedSkills) {
        this.meleeSkills = meleeSkills;
        this.rangedSkills = rangedSkills;
    }

    public Map<Integer, MeleeSkill> getMeleeSkills() {
        return meleeSkills;
    }

    public void setMeleeSkills(Map<Integer, MeleeSkill> meleeSkills) {
        this.meleeSkills = meleeSkills;
    }

    public Map<Integer, RangedSkill> getRangedSkills() {
        return rangedSkills;
    }

    public void setRangedSkills(Map<Integer, RangedSkill> rangedSkills) {
        this.rangedSkills = rangedSkills;
    }

    @Override
    public String toString() {
        return "SkillsModel{" +
                "meleeSkills=" + meleeSkills +
                ", rangedSkills=" + rangedSkills +
                '}';
    }
}
