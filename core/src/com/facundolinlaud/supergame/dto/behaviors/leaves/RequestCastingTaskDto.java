package com.facundolinlaud.supergame.dto.behaviors.leaves;

import com.facundolinlaud.supergame.ai.behavior.leaves.RequestCastingTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;

public class RequestCastingTaskDto extends TaskDto {
    private String skillId;

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    @Override
    public Task build() {
        return new RequestCastingTask(skillId);
    }
}
