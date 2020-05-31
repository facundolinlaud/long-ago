package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.model.skill.Skill;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SkillsFactory {
    private Map<String, Skill> skills;

    public SkillsFactory() {
        Set<String> files = ModelFactory.getSkills();

        this.skills = files.stream()
                .map(ModelFactory::getSkill)
                .collect(Collectors.toMap(Skill::getId, Function.identity()));
    }

    public Skill get(String id) {
        return this.skills.get(id);
    }

    public List<Skill> get(List<String> ids) {
        return ids.stream().map(this::get).collect(Collectors.toList());
    }

    public Map<String, Skill> getSkills() {
        return skills;
    }
}
