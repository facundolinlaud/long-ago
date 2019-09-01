package com.facundolinlaud.supergame.quests;

import com.facundolinlaud.supergame.quests.composites.SequentialTask;
import com.facundolinlaud.supergame.utils.Debugger;

import java.util.List;

public class Quest extends SequentialTask {
    private List<Quest> nextQuests;

    public Quest(List<Quest> nextQuests, Blackboard blackboard, Task...children) {
        super(children);
        this.nextQuests = nextQuests;
        setBlackboard(blackboard);
    }

    @Override
    public void completed() {
        Debugger.debug("[QUEST] Completed. Nexts Quests...");
        nextQuests.forEach(quest -> quest.activate());
    }
}
