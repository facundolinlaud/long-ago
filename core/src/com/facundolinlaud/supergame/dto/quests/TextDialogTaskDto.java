package com.facundolinlaud.supergame.dto.quests;

import com.facundolinlaud.supergame.dto.composite.TaskDto;
import com.facundolinlaud.supergame.quests.Task;
import com.facundolinlaud.supergame.quests.leafs.TextDialogTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public class TextDialogTaskDto extends TaskDto {
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
        return new TextDialogTask(title, message);
    }

    @Override
    public String toString() {
        return "TextDialogTaskDto{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
