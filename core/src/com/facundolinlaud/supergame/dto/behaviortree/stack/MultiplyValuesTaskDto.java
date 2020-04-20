package com.facundolinlaud.supergame.dto.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.stack.MultiplyValuesTask;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, property="@class")
public class MultiplyValuesTaskDto extends TaskDto {
    @Override
    public MultiplyValuesTask build() {
        return new MultiplyValuesTask();
    }
}
