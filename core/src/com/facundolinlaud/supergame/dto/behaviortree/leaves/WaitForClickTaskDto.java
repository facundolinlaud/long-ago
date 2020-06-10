package com.facundolinlaud.supergame.dto.behaviortree.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.behaviortree.leaves.WaitForClickTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class WaitForClickTaskDto extends TaskDto {

    @Override
    public Task build() {
        return new WaitForClickTask();
    }
}
