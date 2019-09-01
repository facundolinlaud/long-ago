package com.facundolinlaud.supergame.dto.quests;

import com.facundolinlaud.supergame.dto.composite.TaskDto;
import com.facundolinlaud.supergame.quests.Task;
import com.facundolinlaud.supergame.quests.leafs.GoldRewardTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public class GoldRewardTaskDto extends TaskDto {
    private int gold;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public Task build() {
        return new GoldRewardTask(gold);
    }

    @Override
    public String toString() {
        return "GoldRewardTaskDto{" +
                "gold=" + gold +
                '}';
    }
}
