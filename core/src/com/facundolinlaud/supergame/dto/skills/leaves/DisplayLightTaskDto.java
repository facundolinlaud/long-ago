package com.facundolinlaud.supergame.dto.skills.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.model.light.LightType;
import com.facundolinlaud.supergame.skills.leaves.DisplayLightTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class DisplayLightTaskDto extends TaskDto {
    private LightType lightType;
    private float distance;
    private float duration;

    public void setLightType(LightType lightType) {
        this.lightType = lightType;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public Task build() {
        return new DisplayLightTask(lightType, distance, duration);
    }
}
