package com.facundolinlaud.supergame.dto.behaviortree;

import com.facundolinlaud.supergame.behaviortree.IterateTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class IterateTaskDto extends SequentialTaskDto {
    @Override
    public Task build() {
        return new IterateTask(buildChildren());
    }
}
