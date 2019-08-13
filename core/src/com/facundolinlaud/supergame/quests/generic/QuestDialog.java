package com.facundolinlaud.supergame.quests.generic;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_CONTINUED;

public abstract class QuestDialog implements Telegraph {
    private MessageDispatcher messageDispatcher;
    private DialogUIController questUIController;

    public QuestDialog(DialogUIController questUIController) {
        this.messageDispatcher = MessageManager.getInstance();
        this.questUIController = questUIController;
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        unsubscribeFromEvent();
        onDialogContinued();

        return false;
    }

    protected void subscribeToEvent() {
        messageDispatcher.addListener(this, QUEST_DIALOG_CONTINUED);
    }

    private void unsubscribeFromEvent(){
        messageDispatcher.removeListener(this, QUEST_DIALOG_CONTINUED);
    }

    protected void showDialog(String title, String text){
        questUIController.showDialog(title, text);
    }

    protected abstract void onDialogContinued();
}
