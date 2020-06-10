package com.facundolinlaud.supergame.dto.behaviortree.composites;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.dto.behaviortree.TaskListDto;

import java.util.LinkedList;
import java.util.stream.Collectors;

public abstract class CompositeTaskDto extends TaskDto {
    private TaskListDto children;

    public TaskListDto getChildren() {
        return children;
    }

    public void setChildren(TaskListDto children) {
        this.children = children;
    }

    protected LinkedList<Task> buildChildren(){
        return getChildren()
                .stream()
                .map(TaskDto::build)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
