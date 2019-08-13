package com.facundolinlaud.supergame.quests.presentation;

import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.quests.conditions.NoQuestCondition;

public class NoQuestPresentationCondition extends NoQuestCondition {
    private Quest quest;

    public NoQuestPresentationCondition(Quest quest) {
        this.quest = quest;
    }

    @Override
    public void done() {
        quest.nextPresentation();
    }
}
