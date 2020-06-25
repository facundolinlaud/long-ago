package com.facundolinlaud.supergame.dto.behaviors.leaves;

import com.facundolinlaud.supergame.ai.behavior.leaves.TargetTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;

public class TargetTaskDto extends TaskDto {
    @Override
    public Task build() {
        return new TargetTask();
    }
}
