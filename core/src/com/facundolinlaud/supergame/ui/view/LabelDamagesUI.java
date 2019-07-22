package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.view.utils.PredefinedActions;
import com.facundolinlaud.supergame.utils.Position;

public class LabelDamagesUI {
    private static float MIN_X_OFFSET = -16.0f;
    private static float MAX_X_OFFSET = 16.0f;

    private Stage stage;
    private Skin skin;

    public LabelDamagesUI(Stage stage, Skin skin) {
        this.stage = stage;
        this.skin = skin;
    }

    public void registerNewDamageLabel(Position position, int damage) {
        Label label = new Label(String.valueOf(damage), this.skin);
        label.setPosition(position.x, position.y);
        label.setColor(Color.FIREBRICK);

        PredefinedActions.addFloatingLabelActions(label, MIN_X_OFFSET, MAX_X_OFFSET);

        stage.addActor(label);
    }
}
