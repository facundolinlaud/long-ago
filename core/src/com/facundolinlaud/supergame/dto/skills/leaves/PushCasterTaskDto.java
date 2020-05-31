package com.facundolinlaud.supergame.dto.skills.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.skills.leaves.PushCasterTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class PushCasterTaskDto extends TaskDto {
    @Override
    public Task build() {
        return new PushCasterTask();
    }
}
