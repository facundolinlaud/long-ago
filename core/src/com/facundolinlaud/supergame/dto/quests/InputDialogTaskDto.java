package com.facundolinlaud.supergame.dto.quests;

import com.facundolinlaud.supergame.dto.composite.TaskDto;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.quests.leaves.InputDialogTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public class InputDialogTaskDto extends TaskDto {
    private String title;
    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Task build() {
        return new InputDialogTask(title, message);
    }

    @Override
    public String toString() {
        return "InputDialogTaskDto{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
