package com.facundolinlaud.supergame.ui.view.overlay.skillsbar;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.ui.view.cross.SkillSlot;

import static com.facundolinlaud.supergame.ui.view.utils.Themes.Label.REGULAR_14;

public class SkillBarSlot extends SkillSlot {
    private Label key;
    private Container keyContainer;
    private Label cooldownLabel;
    private Container cooldownContainer;
    private Image frameOver;

    public SkillBarSlot(Skin skin, String key) {
        super(skin);
        this.key = new Label(key, skin, REGULAR_14);
        this.keyContainer = new Container(this.key);
        this.keyContainer.align(Align.bottomRight).pad(3);

        this.cooldownLabel = new Label("", skin,  REGULAR_14);
        this.cooldownContainer = new Container(this.cooldownLabel);
        this.cooldownContainer.align(Align.center);

        this.frameOver = new Image(skin, "skill-frame-front");

        addActorAfter(getSkillImage(), keyContainer);
        addActorAfter(getSkillImage(), cooldownContainer);
        add(frameOver);
    }

    public void beginCooldown(float cooldown) {
        float delay = Math.max(0f, cooldown - 0.1f);

        cooldownLabel.addAction(new LabelCountdownActor(cooldown, cooldownLabel));
        getSkillImage().addAction(
                Actions.sequence(Actions.alpha(0.3f),
                        Actions.delay(delay),
                        Actions.alpha(1f, 0.1f)));
    }
}
