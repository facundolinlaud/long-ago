package com.facundolinlaud.supergame.dto.behaviortree.composites;

import com.facundolinlaud.supergame.behaviortree.composites.SelectorTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class SelectorTaskDto extends CompositeTaskDto {
    @Override
    public Task build() {
        return new SelectorTask<>(buildChildren());
    }
}
