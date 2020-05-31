package com.facundolinlaud.supergame.dto.behaviortree;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.LinkedList;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.WRAPPER_ARRAY, property="@class")
public class TaskListDto extends LinkedList<TaskDto> {
}
