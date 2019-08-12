package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.ui.view.QuestUI;

public class QuestUIController implements Telegraph {
    private QuestUI questUI;

    public QuestUIController(QuestUI questUI) {
        this.questUI = questUI;
    }

    public void showDialog(String message) {
        questUI.showDialog(message);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return false;
    }
}
