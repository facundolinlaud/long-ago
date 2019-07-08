package com.facundolinlaud.supergame.ui.view.overlay.skillsbar;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.factory.TextureFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.cross.Slot;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

public class SkillBarSlot extends Slot<Skill> {
    private Skill skill;
    private Image skillImage;
    private ImageButton slotButton;
    private Label key;
    private Container keyContainer;
    private Label cooldownLabel;
    private Container cooldownContainer;
    private Image frameOver;

    public SkillBarSlot(Skin skin, String key) {
        this.slotButton = new ImageButton(skin, Themes.IMAGE_BUTTON_SLOT);
        this.skillImage = new Image();

        this.key = new Label(key, skin, "gothic-white");
        this.keyContainer = new Container(this.key);
        this.keyContainer.align(Align.bottomRight).pad(3);

        this.cooldownLabel = new Label("", skin, "gothic-white");
        this.cooldownContainer = new Container(this.cooldownLabel);
        this.cooldownContainer.align(Align.center);

        this.frameOver = new Image(skin, "skill-frame-front");

        add(slotButton);
        add(skillImage);
        add(keyContainer);
        add(cooldownContainer);
        add(frameOver);
    }

    @Override
    public Skill getContent() {
        return this.skill;
    }

    @Override
    public void setContent(Skill skill) {
        this.skill = skill;

        TextureRegion region = TextureFactory.getRegion(skill.getPicturePath());
        TextureRegionDrawable drawable = new TextureRegionDrawable(region);
        this.skillImage.setDrawable(drawable);
    }

    @Override
    public void clearContent() {
        if(this.skill != null){
            this.skillImage.setDrawable(null);
            this.skill = null;
        }
    }

    public void beginCooldown(float cooldown) {
        float delay = Math.max(0f, cooldown - 0.1f);

        cooldownLabel.addAction(new LabelCountdownActor(cooldown, cooldownLabel));
        skillImage.addAction(
                        Actions.sequence(Actions.alpha(0.3f),
                        Actions.delay(delay),
                        Actions.alpha(1f, 0.1f)));
    }
}
