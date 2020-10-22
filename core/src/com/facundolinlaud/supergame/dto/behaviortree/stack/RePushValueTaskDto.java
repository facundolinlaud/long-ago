package com.facundolinlaud.supergame.dto.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.stack.RePushValueTask;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, property="@class")
public class RePushValueTaskDto extends TaskDto {
    private int depth;
    private int times;

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public RePushValueTask build() {
        return new RePushValueTask(depth, times);
    }
}
