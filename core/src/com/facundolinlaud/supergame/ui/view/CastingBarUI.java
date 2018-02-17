package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;

import static com.facundolinlaud.supergame.utils.Dimensions.PX_PER_METER;

/* TODO: refactor this. its awfull */
public class CastingBarUI extends Group implements UI {
    private static final String SKIN_JSON_PATH = "ui/progress_bar_skin/clean-crispy-ui.json";

    private ProgressBar progressBar;
    private Label skillTitle;

    public CastingBarUI() {
        Skin skin = new Skin(Gdx.files.internal(SKIN_JSON_PATH));

        progressBar = new ProgressBar(0f, 100f, 1f, false, skin);
        progressBar.setHeight(0.5f * PX_PER_METER);

        float x = Gdx.graphics.getWidth() / 2 - this.getWidth() / 2 - 1.75f * PX_PER_METER;
        float y = Gdx.graphics.getHeight() / 2 - 0.5f * PX_PER_METER;
        this.setPosition(x, y);

        skillTitle = new Label(null, skin);
        skillTitle.setPosition(PX_PER_METER, - 0.25f * PX_PER_METER);

        this.addActor(progressBar);
        this.addActor(skillTitle);

        this.setVisible(false);
    }

    public void setSkillTitle(String title){
        this.skillTitle.setText(title);
    }

    public void start(String skillName, float castTime) {
        setSkillTitle(skillName);
        this.setVisible(true);

        int repeatCount = 20;
        float interval = castTime / repeatCount;
        final int step = 100 / repeatCount;

        new Timer().scheduleTask(new ProgressBarStepper(repeatCount, step, this), interval, interval, repeatCount);
    }

    @Override
    public Group get() {
        return this;
    }

    class ProgressBarStepper extends Timer.Task {
        private int i = 0;
        private int repeatCount;
        private int step;
        private CastingBarUI castingBarUI;

        public ProgressBarStepper(int repeatCount, int step, CastingBarUI castingBarUI) {
            this.repeatCount = repeatCount;
            this.step = step;
            this.castingBarUI = castingBarUI;
        }

        @Override
        public void run() {
            progressBar.setValue(progressBar.getValue() + step);
            i++;

            if(i == repeatCount){
                progressBar.setValue(0);
                castingBarUI.setVisible(false);
            }
        }
    }
}
