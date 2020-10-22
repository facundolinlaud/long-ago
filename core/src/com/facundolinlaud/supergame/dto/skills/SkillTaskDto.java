package com.facundolinlaud.supergame.dto.skills;

import com.facundolinlaud.supergame.dto.behaviortree.composites.SequentialTaskDto;
import com.facundolinlaud.supergame.skills.SkillTask;

public class SkillTaskDto extends SequentialTaskDto {
    @Override
    public SkillTask build() {
        return new SkillTask(buildChildren());
    }
}
