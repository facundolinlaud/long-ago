package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.view.quest.dialog.QuestDialog;

public class QuestUI {
    private Skin skin;
    private Stage stage;
    private QuestDialog activeDialog;

    public QuestUI(Stage stage, Skin skin) {
        this.stage = stage;
        this.skin = skin;
        this.activeDialog = null;
    }

    public void showDialog(String message){
        if(activeDialog != null)
            activeDialog.remove();

        activeDialog = new QuestDialog(message, skin);
        stage.addActor(activeDialog);
    }
}
