package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.dto.skills.SkillDto;
import com.facundolinlaud.supergame.skills.Skill;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SkillsFactory2 {
    private Map<String, SkillDto> skills;

    public SkillsFactory2() {
        loadSkills();
    }

    private void loadSkills() {
        List<String> files = ModelFactory.getSkills();

        this.skills = files.stream()
            .map(ModelFactory::getSkill)
            .collect(Collectors.toMap(SkillDto::getId, Function.identity()));
    }

    public Skill buildSkill(String skillName){
        return skills.get(skillName).build();
    }
}
