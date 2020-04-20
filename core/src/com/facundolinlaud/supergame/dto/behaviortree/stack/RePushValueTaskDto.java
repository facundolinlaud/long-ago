package com.facundolinlaud.supergame.dto.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.stack.RePushValueTask;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, property="@class")
public class RePushValueTaskDto extends TaskDto {
    private int deepness;
    private int times;

    public void setDeepness(int deepness) {
        this.deepness = deepness;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public RePushValueTask build() {
        return new RePushValueTask(deepness, times);
    }
}
