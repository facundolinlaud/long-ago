package com.facundolinlaud.supergame.ui.view.overlay.notifications;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.view.utils.PredefinedActions;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

public class Notifications {
    public static final int STARTING_Y = 50;
    private static float MIN_X_OFFSET = 0f;
    private static float MAX_X_OFFSET = 0f;

    private Stage stage;
    private Skin skin;

    public Notifications(Stage stage, Skin skin) {
        this.stage = stage;
        this.skin = skin;
    }

    public void popNoManaNotification(){
        popNotification("Not enough mana!", Color.FIREBRICK);
    }

    public void popSkillNotReadyNotification() {
        popNotification("Skill not ready!", Color.FIREBRICK);
    }

    public void popNoAdequateWeaponNotification() {
        popNotification("You don´t have the appropiate weapon for this skill!", Color.FIREBRICK);
    }

    private void popNotification(String text, Color color) {
        Label label = new Label(text, skin, Themes.Label.BOLD_16);
        label.setColor(color);
        label.setX(stage.getWidth() / 2 - label.getWidth() / 2);
        label.setY(STARTING_Y);

        PredefinedActions.addFloatingLabelActions(label, MIN_X_OFFSET, MAX_X_OFFSET);
        stage.addActor(label);
    }
}
