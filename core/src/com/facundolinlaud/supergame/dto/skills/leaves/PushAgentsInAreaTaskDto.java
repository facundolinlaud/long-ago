package com.facundolinlaud.supergame.dto.skills.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.skills.leaves.PushAgentsInAreaTask;
import com.facundolinlaud.supergame.utils.shape.Shape;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class PushAgentsInAreaTaskDto extends TaskDto {
    private Shape shape;
    private Float offset;
    private boolean ignoreCaster;

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setOffset(Float offset) {
        this.offset = offset;
    }

    public void setIgnoreCaster(boolean ignoreCaster) {
        this.ignoreCaster = ignoreCaster;
    }

    @Override
    public Task build() {
        return new PushAgentsInAreaTask(shape, offset, ignoreCaster);
    }
}
