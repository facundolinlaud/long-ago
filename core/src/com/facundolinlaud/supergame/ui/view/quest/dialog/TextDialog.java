package com.facundolinlaud.supergame.ui.view.quest.dialog;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_CONTINUED;

public class TextDialog extends BaseDialog {
    private MessageDispatcher messageDispatcher;

    public TextDialog(Skin skin) {
        super(skin);
        messageDispatcher = MessageManager.getInstance();
    }

    public void onContinueKeyPressed() {
        if(!messageLabel.hasEnded()){
            messageLabel.skipToTheEnd();
        }else{
            hideDialog();
            messageDispatcher.dispatchMessage(QUEST_DIALOG_CONTINUED);
        }
    }
}
