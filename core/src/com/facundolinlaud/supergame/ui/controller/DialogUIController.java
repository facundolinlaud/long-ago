package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.ui.view.DialogUI;

public class DialogUIController implements Telegraph {
    private DialogUI questUI;

    public DialogUIController(DialogUI questUI) {
        this.questUI = questUI;
    }

    public void showTextDialog(String title, String message) {
        questUI.showTextDialog(title, message);
    }

    public void showConfirmDeclineDialog(String title, String message) {
        questUI.showConfirmDeclineDialog(title, message);
    }

    public boolean isBusy(){
        return questUI.isBusy();
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return false;
    }
}
