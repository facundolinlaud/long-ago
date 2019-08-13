package com.facundolinlaud.supergame.quests.presentation;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_ACCEPTED;
import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_DECLINED;

public class QuestInputPresentation implements QuestPresentation, Telegraph {
    private final int[] INPUT_DIALOG_EVENTS = {QUEST_DIALOG_ACCEPTED, QUEST_DIALOG_DECLINED};

    private MessageDispatcher messageDispatcher;
    private DialogUIController questUIController;
    private Quest quest;

    public QuestInputPresentation(DialogUIController questUIController, Quest quest) {
        this.messageDispatcher = MessageManager.getInstance();
        this.questUIController = questUIController;
        this.quest = quest;
    }

    @Override
    public void present() {
        String text = "Hello friend! I would like to ask you for some help! {WAIT} I know this may sound strange, {WAIT}" +
                "but I need 4 skeleton bones in order to finish something I've been working on for quite some time. {WAIT}" +
                "Would you help me?";

        questUIController.showDialog("Title!!", text);
        subscribeToEvents();
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        unsubscribeFromEvents();

        switch(msg.message){
            case QUEST_DIALOG_ACCEPTED:
                onQuestDialogAccepted();
                break;
            case QUEST_DIALOG_DECLINED:
                onQuestDialogDeclined();
                break;
        }

        return false;
    }

    private void subscribeToEvents(){
        messageDispatcher.addListeners(this, INPUT_DIALOG_EVENTS);
    }

    private void unsubscribeFromEvents(){
        messageDispatcher.removeListener(this, INPUT_DIALOG_EVENTS);
    }

    private void onQuestDialogDeclined() {
        quest.resetPresentations();
    }

    private void onQuestDialogAccepted() {
        quest.nextPresentation();
    }
}
