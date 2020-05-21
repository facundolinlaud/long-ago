package com.facundolinlaud.supergame.quests;

import com.facundolinlaud.supergame.behaviortree.KeepTryingTask;
import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.LinkedList;
import java.util.List;

public class Quest extends KeepTryingTask {
    private String name;
    private List<Quest> nextQuests;

    public Quest(String name, Task child) {
        super(child);
        this.name = name;
        this.nextQuests = new LinkedList();
    }

    public void addNextQuest(Quest quest) {
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
