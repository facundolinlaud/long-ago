package com.facundolinlaud.supergame.dto.skills;

import com.facundolinlaud.supergame.dto.behaviortree.SequentialTaskDto;
import com.facundolinlaud.supergame.skills.Skill;

public class SkillDto extends SequentialTaskDto {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Skill build() {
        return new Skill(name, buildChildren());
    }
}
