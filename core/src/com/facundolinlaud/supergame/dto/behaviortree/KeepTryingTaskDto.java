package com.facundolinlaud.supergame.dto.behaviortree;

import com.facundolinlaud.supergame.behaviortree.KeepTryingTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class KeepTryingTaskDto extends DecoratorTaskDto {
    @Override
    public Task build() {
        return new KeepTryingTask(getChild().build());
    }
}
