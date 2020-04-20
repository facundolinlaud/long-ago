package com.facundolinlaud.supergame.dto.skills.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.skills.leaves.SetActionTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class SetActionTaskDto extends TaskDto {
    private Action action;

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public Task build() {
        return new SetActionTask(this.action);
    }
}
