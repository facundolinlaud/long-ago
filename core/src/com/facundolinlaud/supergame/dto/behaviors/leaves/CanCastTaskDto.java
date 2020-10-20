package com.facundolinlaud.supergame.dto.behaviors.leaves;

import com.facundolinlaud.supergame.ai.behavior.leaves.CanCastTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class CanCastTaskDto extends TaskDto {
    private String skillId;

    public CanCastTaskDto() {
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    @Override
    public Task build() {
        return new CanCastTask(skillId);
    }
}
