package com.facundolinlaud.supergame.quests.presentation;

import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.quests.generic.QuestDialog;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

public class QuestDialogPresentation extends QuestDialog implements QuestPresentation{
    private Quest quest;

    public QuestDialogPresentation(DialogUIController questUIController, Quest quest) {
        super(questUIController);
        this.quest = quest;
    }

    @Override
    public void present() {
        String text = "Hello friend! I would like to ask you for some help! {WAIT} I know this may sound strange, {WAIT}" +
                "but I need 4 skeleton bones in order to finish something I've been working on for quite some time. {WAIT}" +
                "Would you help me?";

        showDialog("Fisherman", text);
        subscribeToEvent();
    }

    @Override
    protected void onDialogContinued() {
        nextPresentation();
    }

    private void nextPresentation(){
        quest.nextPresentation();
    }
}
