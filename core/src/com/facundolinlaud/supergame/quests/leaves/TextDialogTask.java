package com.facundolinlaud.supergame.quests.leaves;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.quests.QuestBlackboard;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_CONTINUED;

public class TextDialogTask extends Task<QuestBlackboard> implements Telegraph {
    private DialogUIController dialogUIController;

    private String title;
    private String message;
    private MessageDispatcher messageDispatcher;

    public TextDialogTask(String title, String message) {
        this.title = title;
        this.message = message;
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    protected void onBlackboardAvailable(QuestBlackboard blackboard) {
        this.dialogUIController = blackboard.getUiManager().getDialogUIController();
    }

    @Override
    public void activate() {
        if (dialogUIController.isBusy())
            failed();

        dialogUIController.showTextDialog(title, message);
        subscribeToEvent();
    }

    @Override
    public void completed() {
        unsubscribeFromEvent();
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

    private void unsubscribeFromEvent() {
        messageDispatcher.removeListener(this, QUEST_DIALOG_CONTINUED);
    }
}
