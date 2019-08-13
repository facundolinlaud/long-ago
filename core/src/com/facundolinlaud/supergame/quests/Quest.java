package com.facundolinlaud.supergame.quests;

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
