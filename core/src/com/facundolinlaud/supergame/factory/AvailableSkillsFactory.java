package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.model.skill.*;

import java.util.Map;

public class AvailableSkillsFactory {
    private Map<Integer, Skill> skills;

    public AvailableSkillsFactory() {
        SkillsModel skillsModel = ModelFactory.getAvailableSkillsModel();
        skills = skillsModel.getSkills();
    }

    public Skill getSkillById(int id) {
        return skills.get(id);
    }
}
