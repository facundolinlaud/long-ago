package com.facundolinlaud.supergame.ui.view.profile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Created by facundo on 3/30/16.
 */
public class ProfileTable extends Table {

    private HealthLabel health;
    private FpsLabel fps;
    private Label position;
    private Label bodyPosition;

    public ProfileTable(Skin skin) {
        super(skin);

        this.health = new HealthLabel(skin);
        this.fps = new FpsLabel(skin);
        this.position = new Label("Texture Position: unknown", skin);
        this.bodyPosition = new Label("Body Position: unknown", skin);

        pad(5);
        align(Align.topLeft);

        add(position).left();
        row();
        add(bodyPosition).left();
        row();
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

    public void setPosition(Vector2 position){
        this.position.setText("Texture Position: (" + position.x + ", " + position.y + ")");
    }

    public void setBodyPosition(Vector2 bodyPosition) {
        this.bodyPosition.setText("Body Position: (" + bodyPosition.x + ", " + bodyPosition.y + ")");
    }
}
