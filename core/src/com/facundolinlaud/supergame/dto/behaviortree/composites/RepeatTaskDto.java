package com.facundolinlaud.supergame.dto.behaviortree.composites;

import com.facundolinlaud.supergame.behaviortree.composites.RepeatTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class RepeatTaskDto extends SequentialTaskDto {
    private int times;

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public Task build() {
        return new RepeatTask(buildChildren(), times);
    }
}
