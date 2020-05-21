package com.facundolinlaud.supergame.dto.behaviortree;

public abstract class DecoratorTaskDto extends TaskDto {
    private TaskDto child;

    public TaskDto getChild() {
        return child;
    }

    public void setChild(TaskDto child) {
        this.child = child;
    }
}
