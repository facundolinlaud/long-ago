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
    private MessageDispatcher messageDispatcher;
    private Table buttonsTable;

    public ConfirmDeclineDialog(Skin skin) {
        super(skin);

        messageDispatcher = MessageManager.getInstance();
        setupTable(skin);
    }

    private void setupTable(Skin skin) {
        TextButton acceptButton = new TextButton("Accept", skin, Themes.TextButton.DEFAULT);
        TextButton declineButton = new TextButton("Decline", skin, Themes.TextButton.DEFAULT);

        buttonsTable = new Table(skin);
        buttonsTable.add(acceptButton).padRight(10).height(30).width(70).fill();
        buttonsTable.add(declineButton).padLeft(10).height(30).width(70).fill();

        table.row().center();
        table.add(buttonsTable).padTop(10);

        acceptButton.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hideDialog();
                onQuestDialogAccepted();
            }
        });

        declineButton.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hideDialog();
                onQuestDialogDeclined();
            }
        });
    }

    public void onQuestDialogAccepted(){
        messageDispatcher.dispatchMessage(QUEST_DIALOG_ACCEPTED);
    }

    public void onQuestDialogDeclined(){
        messageDispatcher.dispatchMessage(QUEST_DIALOG_DECLINED);
    }
}
