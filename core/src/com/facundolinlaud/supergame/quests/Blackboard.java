package com.facundolinlaud.supergame.quests;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

public class Blackboard {
    private Entity player;
    private DialogUIController dialogUIController;

    public Blackboard(Entity player, DialogUIController dialogUIController) {
        this.player = player;
        this.dialogUIController = dialogUIController;
    }

    public Entity getPlayer() {
        return player;
    }

    public DialogUIController getDialogUIController() {
        return dialogUIController;
    }
}
