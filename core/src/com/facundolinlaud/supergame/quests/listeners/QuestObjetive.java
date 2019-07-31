package com.facundolinlaud.supergame.quests.listeners;

import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.quests.Quest;

public abstract class QuestObjetive implements Telegraph {
    private String message;
    private Quest quest;

    public QuestObjetive(Quest quest, String message) {
        this.quest = quest;
        this.message = message;
    }

    protected void complete(){
        deactivate();
        quest.onObjectiveCompleted(this);
    }

    public abstract void activate();

    public abstract void deactivate();

    protected String getMessage() {
        return message;
    }
}
