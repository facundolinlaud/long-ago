package com.facundolinlaud.supergame.dto.behaviors.leaves;

import com.facundolinlaud.supergame.ai.behavior.leaves.WaitForActionTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.model.status.Action;

public class WaitForActionTaskDto extends TaskDto {
    private Action action;

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public Task build() {
        return new WaitForActionTask(action);
    }
}
