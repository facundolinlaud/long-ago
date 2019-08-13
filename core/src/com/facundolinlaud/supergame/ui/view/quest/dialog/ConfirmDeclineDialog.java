package com.facundolinlaud.supergame.ui.view.quest.dialog;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_ACCEPTED;
import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_DECLINED;

public class ConfirmDeclineDialog extends BaseDialog {
    private static final int HEIGHT = 140;
    private MessageDispatcher messageDispatcher;
    private Table buttonsTable;

    public ConfirmDeclineDialog(Skin skin) {
        super(skin, HEIGHT);
        messageDispatcher = MessageManager.getInstance();

        setupTable(skin);
    }

    public void onQuestDialogAccepted(){
        messageDispatcher.dispatchMessage(QUEST_DIALOG_ACCEPTED);
    }

    public void onQuestDialogDeclined(){
        messageDispatcher.dispatchMessage(QUEST_DIALOG_DECLINED);
    }

    private void setupTable(Skin skin) {
        TextButton acceptButton = new TextButton("Accept", skin, Themes.TextButton.DEFAULT);
        TextButton declineButton = new TextButton("Decline", skin, Themes.TextButton.DEFAULT);

        buttonsTable = new Table(skin);
        buttonsTable.add(acceptButton).fill().padRight(10).height(40).width(60);
        buttonsTable.add(declineButton).fill().padLeft(10).height(40).width(60);
        container.row().center();
        container.add(buttonsTable).padTop(10).padBottom(10);

        acceptButton.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onQuestDialogAccepted();
                hideDialog();
            }
        });

        declineButton.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onQuestDialogDeclined();
                hideDialog();
            }
        });
    }
}
