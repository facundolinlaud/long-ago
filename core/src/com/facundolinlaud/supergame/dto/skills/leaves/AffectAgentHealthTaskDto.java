package com.facundolinlaud.supergame.dto.skills.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.skills.leaves.AffectAgentHealthTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class AffectAgentHealthTaskDto extends TaskDto {
    private float amount;

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public Task build() {
        return new AffectAgentHealthTask(amount);
    }
}
