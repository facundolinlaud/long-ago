package com.facundolinlaud.supergame.dto.behaviors.leaves;

import com.facundolinlaud.supergame.ai.decisionmaking2.leaves.EnemySeenTask;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class EnemySeenTaskDto extends TaskDto {
    private float viewDistance;
    private List<String> enemyFactions;

    public void setViewDistance(float viewDistance) {
        this.viewDistance = viewDistance;
    }

    public void setEnemyFactions(List<String> enemyFactions) {
        this.enemyFactions = enemyFactions;
    }

    @Override
    public Task build() {
        return new EnemySeenTask(viewDistance, enemyFactions);
    }
}
