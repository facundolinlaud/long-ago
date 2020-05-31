package com.facundolinlaud.supergame.quests.leaves;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.quests.QuestBlackboard;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_ACCEPTED;
import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_DECLINED;

public class InputDialogTask extends Task<QuestBlackboard> implements Telegraph {
    private final int[] DIALOG_EVENTS = { QUEST_DIALOG_ACCEPTED, QUEST_DIALOG_DECLINED };
    private String title;
    private String message;
    private MessageDispatcher messageDispatcher;

    public InputDialogTask(String title, String message) {
        this.title = title;
        this.message = message;
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    public void activate() {
        DialogUIController dialogUIController = getBlackboard().getDialogUIController();

        if(dialogUIController.isBusy())
            failed();

        dialogUIController.showConfirmDeclineDialog(title, message);
        subscribeToEvent();
    }

    @Override
    public void completed() {
        unsubscribeFromEvent();
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
