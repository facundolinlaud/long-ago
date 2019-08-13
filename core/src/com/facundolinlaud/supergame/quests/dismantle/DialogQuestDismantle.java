package com.facundolinlaud.supergame.quests.dismantle;

import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.quests.generic.QuestDialog;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

public class DialogQuestDismantle extends QuestDialog implements QuestDismantle {
    private Quest quest;

    public DialogQuestDismantle(DialogUIController questUIController, Quest quest) {
        super(questUIController);
        this.quest = quest;
    }

    @Override
    public void dismantle() {
        String text = "Thank you very much for helping me! Here is your reward!";
        showDialog("Fisherman", text);
        subscribeToEvent();
    }

    @Override
    protected void onDialogContinued() {
        nextDismantle();
    }

    private void nextDismantle(){
        quest.nextDismantle();
    }
}
