package com.facundolinlaud.supergame.dto.behaviors;

import com.facundolinlaud.supergame.ai.decisionmaking2.BehaviorTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.composites.CompositeTaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class BehaviorTaskDto extends CompositeTaskDto {
    @Override
    public Task build() {
        return new BehaviorTask(buildChildren());
    }
}
