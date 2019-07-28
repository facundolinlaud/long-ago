package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.model.skill.Skill;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SkillsFactory {
    private Map<Integer, Skill> skills;

    public SkillsFactory() {
        this.skills = ModelFactory.getSkillsModel();
    }

    public Skill get(int id) {
        return skills.get(id);
    }

    public List<Skill> get(List<Integer> ids){
        return ids.stream().map(id -> get(id)).collect(Collectors.toList());
    }

    public Map<Integer, Skill> getSkills() {
        return skills;
    }
}
