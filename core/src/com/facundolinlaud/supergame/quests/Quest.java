package com.facundolinlaud.supergame.quests;

import com.facundolinlaud.supergame.quests.composites.SequentialTask;
import com.facundolinlaud.supergame.utils.Debugger;

import java.util.LinkedList;
import java.util.List;

public class Quest extends SequentialTask {
    private String name;
    private List<Quest> nextQuests;

    public Quest(String name, LinkedList<Task> children) {
        super(children);
        this.name = name;
        this.nextQuests = new LinkedList();
    }

    public void addNextQuest(Quest quest){
        this.nextQuests.add(quest);
    }

    public String getName() {
        return name;
    }

    @Override
    public void completed() {
        Debugger.debug("[QUEST] Completed. Nexts Quests...");
        nextQuests.forEach(quest -> quest.activate());
    }
}
