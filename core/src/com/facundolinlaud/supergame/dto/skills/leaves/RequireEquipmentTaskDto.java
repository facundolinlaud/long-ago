package com.facundolinlaud.supergame.dto.skills.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.model.equip.EquipType;
import com.facundolinlaud.supergame.skills.leaves.RequireEquipmentTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class RequireEquipmentTaskDto extends TaskDto {
    private EquipType equipType;

    public void setEquipType(EquipType equipType) {
        this.equipType = equipType;
    }

    @Override
    public Task build() {
        return new RequireEquipmentTask(equipType);
    }
}
