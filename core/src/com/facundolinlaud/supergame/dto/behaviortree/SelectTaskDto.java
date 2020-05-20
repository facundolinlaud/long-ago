package com.facundolinlaud.supergame.dto.behaviortree;

import com.facundolinlaud.supergame.behaviortree.SelectTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class SelectTaskDto extends CompositeTaskDto {
    @Override
    public Task build() {
        return new SelectTask<>(buildChildren());
    }
}
