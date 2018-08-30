package com.facundolinlaud.supergame.model.skill;

import java.util.Map;

public class SkillsModel {
    private Map<Integer, Skill> skills;

    public SkillsModel(){}

    public SkillsModel(Map<Integer, Skill> skills) {
        this.skills = skills;
    }

    public Map<Integer, Skill> getSkills() {
        return skills;
    }

    public void setSkills(Map<Integer, Skill> skills) {
        this.skills = skills;
    }
}
