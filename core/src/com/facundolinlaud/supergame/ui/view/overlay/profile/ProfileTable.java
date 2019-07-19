package com.facundolinlaud.supergame.ui.view.overlay.profile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import static com.facundolinlaud.supergame.ui.view.utils.Themes.Label.REGULAR_14;
/**
 * Created by facundo on 3/30/16.
 */
public class ProfileTable extends Table {

    private ProgressBar healthBar;
    private ProgressBar manaBar;

    private Label fpsLabel;
    private Label positionLabel;
    private Label bodyPositionLabel;

    public ProfileTable(Skin skin) {
        super(skin);

        this.healthBar = new ProgressBar(0, 100, 1, false, skin, "gothic-health");
        this.healthBar.setSize(300, 80);

        this.manaBar = new ProgressBar(0, 100, 1, false, skin, "gothic-mana");
        this.manaBar.setSize(300, 80);
        this.manaBar.setValue(50);

        this.fpsLabel = new Label("FPS: unknown", skin, REGULAR_14);
        this.positionLabel = new Label("Texture Position: unknown", skin, REGULAR_14);
        this.bodyPositionLabel = new Label("Body Position: unknown", skin, REGULAR_14);

        pad(5);
        align(Align.topLeft);

        add(healthBar).top().left().width(300).height(20);
        row();
        add(manaBar).top().left().width(300).height(20);
        row();
        add(positionLabel).left();
        row();
        add(bodyPositionLabel).left();
        row();
        add(fpsLabel).left();
    }

    public void setHealth(float health) {
        if(health >= 0 || health <= 100)
            this.healthBar.setValue(health);
    }

    public void setFPS(int fps) {
        this.fpsLabel.setText("FPS: " + fps);
    }

    public void setPosition(Vector2 position){
        this.positionLabel.setText("Texture Position: (" + position.x + ", " + position.y + ")");
    }

    public void setBodyPosition(Vector2 bodyPosition) {
        this.bodyPositionLabel.setText("Body Position: (" + bodyPosition.x + ", " + bodyPosition.y + ")");
    }
}
