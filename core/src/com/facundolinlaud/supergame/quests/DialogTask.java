package com.facundolinlaud.supergame.quests;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;
import com.facundolinlaud.supergame.utils.Debugger;

import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_CONTINUED;

public class DialogTask extends Task implements Telegraph {
    private DialogUIController dialogUIController;
    private MessageDispatcher messageDispatcher;

    public DialogTask(Blackboard blackboard) {
        this.dialogUIController = blackboard.getDialogUIController();
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    public void activate() {
        String title = "Fisherman";
        String text = "Hello friend! I would like to ask you for some help! {WAIT} I know this may sound strange, {WAIT}" +
                "but I need 4 skeleton bones in order to finish something I've been working on for quite some time. {WAIT}" +
                "Would you help me?";

        dialogUIController.showDialog(title, text);
        subscribeToEvent();
        Debugger.debug("[DIALOG] Activating");
    }

    @Override
    public void completed() {
        unsubscribeFromEvent();
        Debugger.debug("[GOLD] Completed");
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
