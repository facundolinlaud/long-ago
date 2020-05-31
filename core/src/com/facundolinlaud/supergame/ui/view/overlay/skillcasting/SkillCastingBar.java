package com.facundolinlaud.supergame.ui.view.overlay.skillcasting;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

public class SkillCastingBar extends Table {
    public static final int DISTANCE_FROM_SKILL_BAR = 30;
    private ProgressBar castingBar;
    private Label skillLabel;

    public SkillCastingBar(Skin skin) {
        super(skin);
        setVisible(false);
        setWidth(400);
        padBottom(DISTANCE_FROM_SKILL_BAR);

        this.castingBar = new ProgressBar(0, 1, 0.01f,
                false, skin, Themes.ProgressBar.CASTING_BAR);
//        this.castingBar.getStyle().background.setMinHeight(40);
//        this.castingBar.getStyle().knobBefore.setMinHeight(40);
        this.skillLabel = new Label("", skin, Themes.Label.SMALL_CAPS_14);

        add(this.castingBar).fill();
        row();
        add(this.skillLabel);
    }

    public void updateCastingBar(String skillName, float castingBarValue){
        boolean isStillCasting = castingBarValue < 1;
        this.setVisible(isStillCasting);

        skillLabel.setText(skillName);
        castingBar.setValue(castingBarValue);
    }
}
