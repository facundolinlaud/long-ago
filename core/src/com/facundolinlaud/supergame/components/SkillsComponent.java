package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillType;

import java.util.List;
import java.util.stream.Collectors;

public class SkillsComponent implements Component {
    private List<Skill> skills;

    public SkillsComponent(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Skill> getMeleeSkills(){
        return skills.stream().filter(skill -> SkillType.NORMAL.equals(skill.getSkillType()))
                .collect(Collectors.toList());
    }

    public List<Skill> getRangedSkills(){
        return skills.stream().filter(skill -> !SkillType.NORMAL.equals(skill.getSkillType()))
                .collect(Collectors.toList());
    }

    public void add(Skill skill){
        this.skills.add(skill);
    }
}
