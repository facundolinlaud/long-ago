package com.facundolinlaud.supergame.ui.view.overlay.notifications;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.facundolinlaud.supergame.ui.view.utils.PredefinedActions;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

public class Notifications extends VerticalGroup {
    private static float MIN_X_OFFSET = 0f;
    private static float MAX_X_OFFSET = 0f;

    private Skin skin;

    public Notifications(Skin skin) {
        this.skin = skin;
    }

    public void popNoManaNotification(){
        popNotification("Not enough mana!", Color.FIREBRICK);
    }

    public void popSkillNotReadyNotification() {
        popNotification("Skill not ready!", Color.FIREBRICK);
    }

    /* TODO: chequear eventualmente si las rows no se están eliminando junto con los labels */
    private void popNotification(String text, Color color) {
        Label label = new Label(text, skin, Themes.Label.BOLD_16);
        label.setColor(color);
        PredefinedActions.addFloatingLabelActions(label, MIN_X_OFFSET, MAX_X_OFFSET);

        addActor(label);
    }
}
