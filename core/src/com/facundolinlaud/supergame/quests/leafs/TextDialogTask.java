package com.facundolinlaud.supergame.quests.leafs;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.quests.QuestBlackboard;
import com.facundolinlaud.supergame.quests.Task;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;
import com.facundolinlaud.supergame.utils.Debugger;

import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_CONTINUED;

public class TextDialogTask extends Task<QuestBlackboard> implements Telegraph {
    private String title;
    private String message;
    private MessageDispatcher messageDispatcher;

    public TextDialogTask(String title, String message) {
        this.title = title;
        this.message = message;
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    public void activate() {
        DialogUIController dialogUIController = getBlackboard().getDialogUIController();

        if(dialogUIController.isBusy())
            failed();

        dialogUIController.showTextDialog(title, message);
        subscribeToEvent();
        Debugger.debug("[DIALOG] Activating");
    }

    @Override
    public void completed() {
        unsubscribeFromEvent();
        Debugger.debug("[DIALOG] Completed");
        super.completed();
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        completed();
        return false;
    }

    private void subscribeToEvent() {
        messageDispatcher.addListener(this, QUEST_DIALOG_CONTINUED);
    }

    private void unsubscribeFromEvent(){
        messageDispatcher.removeListener(this, QUEST_DIALOG_CONTINUED);
    }
}
