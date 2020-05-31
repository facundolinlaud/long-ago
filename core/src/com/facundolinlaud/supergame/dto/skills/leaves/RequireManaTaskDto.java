package com.facundolinlaud.supergame.dto.skills.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.skills.leaves.RequireManaTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class RequireManaTaskDto extends TaskDto {
    private int mana;

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public Task build() {
        return new RequireManaTask(mana);
    }
}
