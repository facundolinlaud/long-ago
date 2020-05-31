package com.facundolinlaud.supergame.dto.behaviortree.stack;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.stack.PushValueTask;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class PushValueTaskDto extends TaskDto {
    private Value value;

    public void setString(String string) {
        this.value = new Value(string);
    }

    public void setNumber(float f) {
        this.value = new Value(f);
    }

    public void setEntity(Entity e) {
        this.value = new Value(e);
    }

    @Override
    public PushValueTask build() {
        return new PushValueTask(value);
    }
}
