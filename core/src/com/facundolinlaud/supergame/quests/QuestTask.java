package com.facundolinlaud.supergame.quests;

import com.facundolinlaud.supergame.behaviortree.KeepTryingTask;
import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class QuestTask extends KeepTryingTask {
    private String name;
    private List<QuestTask> nextQuests;

    public QuestTask(String name, Task child) {
        super(child);
        this.name = name;
        this.nextQuests = new LinkedList();
        setStack(new Stack());
    }

    public void addNextQuest(QuestTask quest) {
        nextQuests.add(quest);
    }

    public String getName() {
        return name;
    }

    @Override
    public void completed() {
        nextQuests.forEach(quest -> quest.activate());
    }
}
