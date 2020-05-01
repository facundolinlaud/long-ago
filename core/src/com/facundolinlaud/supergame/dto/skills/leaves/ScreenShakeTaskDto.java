package com.facundolinlaud.supergame.dto.skills.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.skills.leaves.ScreenShakeTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class ScreenShakeTaskDto extends TaskDto {
    private float power;
    private float duration;

    public void setPower(float power) {
        this.power = power;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public Task build() {
        return new ScreenShakeTask(power, duration);
    }
}
