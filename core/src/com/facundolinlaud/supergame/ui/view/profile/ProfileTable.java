package com.facundolinlaud.supergame.ui.view.profile;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Created by facundo on 3/30/16.
 */
public class ProfileTable extends Table {

    private HealthLabel health;
    private FpsLabel fps;

    public ProfileTable(Skin skin) {
        super(skin);

        this.health = new HealthLabel(skin);
        this.fps = new FpsLabel(skin);

        pad(5);
        align(Align.topLeft);

        add(health).left();
        row();
        add(fps).left();
    }

    public void setHealth(float health) {
        this.health.setHealth(health);
    }

    public void setFPS(int fps) {
        this.fps.setFPS(fps);
    }
}
