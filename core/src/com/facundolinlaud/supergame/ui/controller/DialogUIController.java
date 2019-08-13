package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.ui.view.DialogUI;

public class DialogUIController implements Telegraph {
    private DialogUI questUI;

    public DialogUIController(DialogUI questUI) {
        this.questUI = questUI;
    }

    public void showDialog(String title, String message) {
        questUI.showDialog(title, message);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return false;
    }
}
