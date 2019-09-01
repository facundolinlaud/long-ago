package com.facundolinlaud.supergame.dto.quests;

import com.facundolinlaud.supergame.dto.composite.TaskDto;
import com.facundolinlaud.supergame.quests.Task;
import com.facundolinlaud.supergame.quests.leafs.SlayTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public class SlayTaskDto extends TaskDto {
    private int agentId;
    private int total;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public Task build() {
        return new SlayTask(agentId, total);
    }

    @Override
    public String toString() {
        return "SlayTaskDto{" +
                "agentId=" + agentId +
                ", total=" + total +
                '}';
    }
}
