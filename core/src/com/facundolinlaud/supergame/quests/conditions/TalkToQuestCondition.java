package com.facundolinlaud.supergame.quests.conditions;

import com.facundolinlaud.supergame.quests.Quest;

public class TalkToQuestCondition implements QuestCondition {
    private Quest quest;

    public TalkToQuestCondition(Quest quest) {
        this.quest = quest;
    }

    @Override
    public void activate() {

    }

    @Override
    public void done() {
        quest.activate();
    }
}
