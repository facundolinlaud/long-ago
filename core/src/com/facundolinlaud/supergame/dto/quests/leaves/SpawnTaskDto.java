package com.facundolinlaud.supergame.dto.quests.leaves;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.quests.leaves.SpawnTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class SpawnTaskDto extends TaskDto {
    private String agentId;
    private String agentTag;
    private Vector2 position;

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setAgentTag(String agentTag) {
        this.agentTag = agentTag;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public Task build() {
        return new SpawnTask(agentId, agentTag, position);
    }
}
