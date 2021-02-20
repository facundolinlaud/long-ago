package com.facundolinlaud.supergame.dto.behaviors.leaves;

import com.facundolinlaud.supergame.ai.behavior.leaves.EnemyAttackedTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class EnemyAttackedTaskDto extends TaskDto {
    @Override
    public Task build() {
        return new EnemyAttackedTask();
    }
}
