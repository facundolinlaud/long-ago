package com.facundolinlaud.supergame.dto.behaviortree;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.WaitTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class WaitTaskDto extends TaskDto {
    private float time;

    public void setTime(float time) {
        this.time = time;
    }

    @Override
    public Task build() {
        return new WaitTask(this.time);
    }
}
