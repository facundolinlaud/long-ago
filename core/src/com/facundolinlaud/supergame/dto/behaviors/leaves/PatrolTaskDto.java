package com.facundolinlaud.supergame.dto.behaviors.leaves;

import com.facundolinlaud.supergame.ai.decisionmaking2.leaves.PatrolTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class PatrolTaskDto extends TaskDto {
    @Override
    public Task build() {
        return new PatrolTask();
    }
}
