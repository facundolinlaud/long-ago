package com.facundolinlaud.supergame.dto.behaviortree.composites;

import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;

public abstract class DecoratorTaskDto extends TaskDto {
    private TaskDto child;

    public TaskDto getChild() {
        return child;
    }

    public void setChild(TaskDto child) {
        this.child = child;
    }
}
