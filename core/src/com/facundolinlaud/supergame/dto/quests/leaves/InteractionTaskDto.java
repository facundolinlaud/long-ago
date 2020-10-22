package com.facundolinlaud.supergame.dto.quests.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.quests.leaves.InteractionTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class InteractionTaskDto extends TaskDto {
    private String agentTag;

    public void setAgentTag(String agentTag) {
        this.agentTag = agentTag;
    }

    @Override
    public Task build() {
        return new InteractionTask(agentTag);
    }
}
