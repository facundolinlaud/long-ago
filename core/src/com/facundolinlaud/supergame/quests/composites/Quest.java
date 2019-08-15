package com.facundolinlaud.supergame.quests.composites;

import com.facundolinlaud.supergame.quests.Task;
import com.facundolinlaud.supergame.utils.Debugger;

import java.util.LinkedList;
import java.util.List;

public class Quest extends SequentialTask {
    private List<Quest> nextQuests;

    public Quest(LinkedList<Task> children, List<Quest> nextQuests) {
        super(children);
        this.nextQuests = nextQuests;
    }

    @Override
    public void completed() {
        Debugger.debug("[QUEST] Completed. Nexts Quests...");
        nextQuests.forEach(quest -> quest.activate());
    }
}
