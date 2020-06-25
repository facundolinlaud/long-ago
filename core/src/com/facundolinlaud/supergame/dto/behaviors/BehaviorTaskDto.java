package com.facundolinlaud.supergame.dto.behaviors;

import com.facundolinlaud.supergame.ai.behavior.BehaviorTask;
import com.facundolinlaud.supergame.dto.behaviortree.composites.CompositeTaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class BehaviorTaskDto extends CompositeTaskDto {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public BehaviorTask build() {
        return new BehaviorTask(buildChildren());
    }
}
