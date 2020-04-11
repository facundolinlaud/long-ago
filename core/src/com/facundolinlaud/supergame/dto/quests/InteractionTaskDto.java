package com.facundolinlaud.supergame.dto.quests;

import com.facundolinlaud.supergame.dto.composite.TaskDto;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.quests.leaves.InteractionTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public class InteractionTaskDto extends TaskDto {
    private int agentId;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    @Override
    public Task build() {
        return new InteractionTask(agentId);
    }

    @Override
    public String toString() {
        return "InteractionTaskDto{" +
                "agentId=" + agentId +
                '}';
    }
}
