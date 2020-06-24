package com.facundolinlaud.supergame.dto.behaviors.leaves;

import com.facundolinlaud.supergame.ai.decisionmaking2.leaves.TraverseToTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;

public class TraverseToTaskDto extends TaskDto {
    @Override
    public Task build() {
        return new TraverseToTask();
    }
}
