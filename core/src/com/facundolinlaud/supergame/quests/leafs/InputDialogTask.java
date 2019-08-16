package com.facundolinlaud.supergame.quests.leafs;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.quests.Blackboard;
import com.facundolinlaud.supergame.quests.Task;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;
import com.facundolinlaud.supergame.utils.Debugger;

import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_ACCEPTED;
import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_DECLINED;

public class InputDialogTask extends Task implements Telegraph {
    private final int[] DIALOG_EVENTS = { QUEST_DIALOG_ACCEPTED, QUEST_DIALOG_DECLINED };
    private String title;
    private String message;
    private DialogUIController dialogUIController;
    private MessageDispatcher messageDispatcher;

    public InputDialogTask(String title, String message, Blackboard blackboard) {
        this.title = title;
        this.message = message;
        this.dialogUIController = blackboard.getDialogUIController();
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    public void activate() {
        if(dialogUIController.isBusy())
            failed();

        dialogUIController.showConfirmDeclineDialog(title, message);
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
        switch(msg.message){
            case QUEST_DIALOG_ACCEPTED:
                completed();
                break;
            case QUEST_DIALOG_DECLINED:
                failed();
                break;
        }

        return false;
    }

    @Override
    public void failed(){
        unsubscribeFromEvent();
        super.failed();
    }

    private void subscribeToEvent() {
        messageDispatcher.addListeners(this, DIALOG_EVENTS);
    }

    private void unsubscribeFromEvent(){
        messageDispatcher.removeListener(this, DIALOG_EVENTS);
    }
}
