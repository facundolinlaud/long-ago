package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.view.quest.dialog.QuestDialog;

public class DialogUI {
    private QuestDialog dialog;

    public DialogUI(Stage stage, Skin skin) {
        dialog = new QuestDialog(skin);
        stage.addActor(dialog.get());
    }

    public void showDialog(String title, String message){
        dialog.showDialog(title, message);
    }

    public void onContinueKeyPressed() {
        dialog.onContinueKeyPressed();
    }
}
