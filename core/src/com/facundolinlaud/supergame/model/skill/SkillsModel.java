package com.facundolinlaud.supergame.model.skill;

import java.util.Map;

public class SkillsModel {
    private Map<Integer, MeleeSkill> meleeSkills;
    private Map<Integer, RangedSkill> rangedSkills;
    private Map<Integer, SpellSkill> spellSkills;

    public SkillsModel(Map<Integer, MeleeSkill> meleeSkills, Map<Integer, RangedSkill> rangedSkills, Map<Integer, SpellSkill> spellSkills) {
        this.meleeSkills = meleeSkills;
        this.rangedSkills = rangedSkills;
        this.spellSkills = spellSkills;
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

    public Map<Integer, SpellSkill> getSpellSkills() {
        return spellSkills;
    }

    public void setSpellSkills(Map<Integer, SpellSkill> spellSkills) {
        this.spellSkills = spellSkills;
    }

    @Override
    public String toString() {
        return "SkillsModel{" +
                "meleeSkills=" + meleeSkills +
                ", rangedSkills=" + rangedSkills +
                ", spellSkills=" + spellSkills +
                '}';
    }
}
