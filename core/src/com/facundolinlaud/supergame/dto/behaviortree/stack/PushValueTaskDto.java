package com.facundolinlaud.supergame.dto.behaviortree.stack;

import com.facundolinlaud.supergame.behaviortree.stack.PushValueTask;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, property="@class")
public class PushValueTaskDto extends TaskDto {
    private Value value;

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public PushValueTask build() {
        return new PushValueTask(value);
    }
}
