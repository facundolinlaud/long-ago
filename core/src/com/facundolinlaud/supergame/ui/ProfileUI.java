package com.facundolinlaud.supergame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by facundo on 3/25/16.
 */
public class ProfileUI implements UI {
    public static final String DEFAULT_THEME = "default";

    private final Label healthLabel;
    private final Label fpsLabel;

    public ProfileUI(Table parentTable, Skin skin) {
        Table table = new Table(skin);
        table.setWidth(300);
        table.setHeight(300);
        table.setPosition(0, 0);
        table.pad(5);

        healthLabel = new Label("HEALTH", skin, DEFAULT_THEME);
        fpsLabel = new Label("FPS", skin, DEFAULT_THEME);

        table.add(healthLabel).colspan(0);
        table.row();
        table.add(fpsLabel);

        parentTable.add(table);
    }

    public void setHealth(float health){
        healthLabel.setText("HEALTH: " + health);
    }

    public void setFPS(int fps){
        fpsLabel.setText("FPS: " + fps);
    }
}
