package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;

import static com.facundolinlaud.supergame.utils.Dimensions.PX_PER_METER;

/* TODO: refactor this. it's awful */

public class SkillCastingUI extends Group implements UI {
    private ProgressBar progressBar;
    private Label skillTitle;

    public SkillCastingUI(Skin skin) {
        progressBar = new ProgressBar(0f, 100f, 1f, false,
                skin, "gothic-skillcast");
        progressBar.setPosition(PX_PER_METER, 0);
        progressBar.setHeight(0.25f * PX_PER_METER);
        progressBar.setWidth(2 * PX_PER_METER);

        float x = Gdx.graphics.getWidth() / 2 - this.getWidth() / 2 - 1.5f * PX_PER_METER;
        float y = Gdx.graphics.getHeight() / 2 - 0.5f * PX_PER_METER;
        this.setPosition(x, y);

        skillTitle = new Label(null, skin);
        skillTitle.setPosition(PX_PER_METER, - 0.25f * PX_PER_METER);

        this.addActor(progressBar);
        this.addActor(skillTitle);

        this.setVisible(false);
    }

    public void update(String skillName, float castingBarValue){
        boolean isStillCasting = castingBarValue > 0;
        this.setVisible(isStillCasting);

        skillTitle.setText(skillName);
        progressBar.setValue(castingBarValue);
    }

    @Override
    public Group get() {
        return this;
    }
}
