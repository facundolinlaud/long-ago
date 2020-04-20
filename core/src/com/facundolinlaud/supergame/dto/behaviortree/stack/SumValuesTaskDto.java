package com.facundolinlaud.supergame.dto.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.stack.SumValuesTask;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, property="@class")
public class SumValuesTaskDto extends TaskDto {
    @Override
    public SumValuesTask build() {
        return new SumValuesTask();
    }
}
