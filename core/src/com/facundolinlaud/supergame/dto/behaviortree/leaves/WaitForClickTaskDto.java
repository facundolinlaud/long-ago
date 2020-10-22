package com.facundolinlaud.supergame.dto.behaviortree.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.leaves.WaitForClickTask;
import com.facundolinlaud.supergame.dto.ColorDto;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.utils.shape.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class WaitForClickTaskDto extends TaskDto {
    private Shape areaShape;
    private ColorDto areaColor;

    public void setAreaShape(Shape areaShape) {
        this.areaShape = areaShape;
    }

    public void setAreaColor(ColorDto areaColor) {
        this.areaColor = areaColor;
    }

    @Override
    public Task build() {
        if (areaShape != null) {
            return new WaitForClickTask(areaShape, areaColor.build());
        }

        return new WaitForClickTask();
    }
}
