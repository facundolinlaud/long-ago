package com.facundolinlaud.supergame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Created by facundo on 3/25/16.
 */
public class ProfileUI implements UI {
    public static final String DEFAULT_THEME = "default";

    private final Table table;
    private final Label healthLabel;
    private final Label fpsLabel;

    public ProfileUI(Skin skin) {
        this.table = new Table(skin);
        this.table.align(Align.topLeft);
        this.table.pad(5);

        this.healthLabel = new Label("HEALTH", skin, DEFAULT_THEME);
        this.fpsLabel = new Label("FPS", skin, DEFAULT_THEME);

        this.table.add(healthLabel).left();
        this.table.row();
        this.table.add(fpsLabel).left();
    }

    @Override
    public Table getUI() {
        return this.table;
    }

    public void setHealth(float health){
        healthLabel.setText("HEALTH: " + health);
    }

    public void setFPS(int fps){
        fpsLabel.setText("FPS: " + fps);
    }
}
