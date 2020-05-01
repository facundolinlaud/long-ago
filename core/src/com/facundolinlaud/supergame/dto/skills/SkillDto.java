package com.facundolinlaud.supergame.dto.skills;

import com.facundolinlaud.supergame.dto.behaviortree.SequentialTaskDto;
import com.facundolinlaud.supergame.skills.Skill;

public class SkillDto extends SequentialTaskDto {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Skill build() {
        return new Skill(id, buildChildren());
    }
}
