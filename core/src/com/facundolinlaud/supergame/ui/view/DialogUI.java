package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.view.quest.dialog.ConfirmDeclineDialog;
import com.facundolinlaud.supergame.ui.view.quest.dialog.TextDialog;

public class DialogUI {
    private TextDialog textDialog;
    private ConfirmDeclineDialog confirmCancelDialog;

    public DialogUI(Stage stage, Skin skin) {
        textDialog = new TextDialog(skin);
        confirmCancelDialog = new ConfirmDeclineDialog(skin);

        stage.addActor(textDialog.get());
        stage.addActor(confirmCancelDialog.get());
    }

    public void showTextDialog(String title, String message){
        if(confirmCancelDialog.get().isVisible())
            confirmCancelDialog.onQuestDialogDeclined();

        textDialog.showDialog(title, message);
    }

    public void showConfirmDeclineDialog(String title, String message){
        onContinueKeyPressed();
        confirmCancelDialog.showDialog(title, message);
    }

    public void onContinueKeyPressed() {
        if(textDialog.get().isVisible())
            textDialog.onContinueKeyPressed();
    }

    public boolean isBusy() {
        return textDialog.get().isVisible() || confirmCancelDialog.get().isVisible();
    }
}
