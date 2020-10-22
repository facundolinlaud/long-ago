package com.facundolinlaud.supergame.dto.behaviors.leaves;

import com.facundolinlaud.supergame.ai.behavior.leaves.PursueTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;

public class PursueTaskDto extends TaskDto {
    private float seekedProximity;

    public void setSeekedProximity(float seekedProximity) {
        this.seekedProximity = seekedProximity;
    }

    @Override
    public Task build() {
        return new PursueTask(seekedProximity);
    }
}
