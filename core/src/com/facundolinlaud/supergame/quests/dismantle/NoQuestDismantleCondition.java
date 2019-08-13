package com.facundolinlaud.supergame.quests.dismantle;

import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.quests.conditions.NoQuestCondition;

public class NoQuestDismantleCondition extends NoQuestCondition {
    private Quest quest;


    public NoQuestDismantleCondition(Quest quest) {
        this.quest = quest;
    }

    @Override
    public void done() {
        this.quest.nextDismantle();
    }
}
