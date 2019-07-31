package com.facundolinlaud.supergame.quests.presentation;

import com.facundolinlaud.supergame.quests.Quest;

public class QuestAutoAcceptPresentation implements QuestPresentation {
    private Quest quest;

    public QuestAutoAcceptPresentation(Quest quest) {
        this.quest = quest;
    }

    @Override
    public void present() {
        quest.start();
    }
}
