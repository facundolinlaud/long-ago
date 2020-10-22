package com.facundolinlaud.supergame.dto.behaviortree.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.behaviortree.leaves.PushAgentTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class PushAgentTaskDto extends TaskDto {
    @Override
    public Task build() {
        return new PushAgentTask();
    }
}
