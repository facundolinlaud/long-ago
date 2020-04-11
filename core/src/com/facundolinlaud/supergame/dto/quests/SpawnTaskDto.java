package com.facundolinlaud.supergame.dto.quests;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.dto.composite.TaskDto;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.quests.leaves.SpawnTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public class SpawnTaskDto extends TaskDto {
    private int agentId;
    private Vector2 position;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public Task build() {
        return new SpawnTask(agentId, position);
    }

    @Override
    public String toString() {
        return "SpawnTaskDto{" +
                "agentId=" + agentId +
                ", position=" + position +
                '}';
    }
}
