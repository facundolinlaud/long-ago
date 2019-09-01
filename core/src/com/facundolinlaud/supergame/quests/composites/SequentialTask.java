package com.facundolinlaud.supergame.quests.composites;

import com.facundolinlaud.supergame.quests.Task;
import com.facundolinlaud.supergame.utils.Debugger;

public class SequentialTask extends CompositeTask {
    public SequentialTask(Task...children) {
        super(children);
    }

    @Override
    public void childCompleted(Task child) {
        if(children.isEmpty()) {
            completed();
        }else{
            activate();
        }
    }

    @Override
    public void activate() {
        Debugger.debug("[SEQUENTIAL] Next...");
        Task child = children.removeFirst();

        completed.add(child);
        child.activate();
    }
}
