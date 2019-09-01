package com.facundolinlaud.supergame.dto.composite;

import com.facundolinlaud.supergame.quests.Task;
import com.facundolinlaud.supergame.quests.composites.ParallelTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public class ParallelTaskDto extends CompositeTaskDto {
    @Override
    public Task build() {
        return new ParallelTask(buildChildren());
    }
}
