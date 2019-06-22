package com.facundolinlaud.supergame.ui.view.skillsbar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.cross.Slot;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

public class SkillbarSlot extends Slot<Skill> {
    private static final int HEIGHT = 32;
    private static final int WIDTH = 32;

    private Skill skill;
    private Image skillImage;
    private ImageButton slotButton;
    private Image cooldownImage;

    public SkillbarSlot(Skin skin) {
        this.slotButton = new ImageButton(skin, Themes.IMAGE_BUTTON_SLOT);
        this.cooldownImage = new Image();
        this.cooldownImage.setColor(Color.BLACK);
        this.cooldownImage.setSize(WIDTH, HEIGHT);
        this.cooldownImage.setPosition(slotButton.getX(), slotButton.getY());

        setSize(WIDTH, HEIGHT);
        add(slotButton);
        add(cooldownImage);
    }

    @Override
    public Skill getContent() {
        return this.skill;
    }

    @Override
    public void setContent(Skill skill) {
        this.skill = skill;
        this.skillImage = new Image(new Texture(skill.getPicturePath()));
        add(this.skillImage);
    }

    @Override
    public void clearContent() {
        if(this.skill != null){
            removeActor(this.skillImage);
            this.skill = null;
        }
    }

    public void beginCooldown(float cooldown) {
        skillImage.addAction(Actions.alpha(0));
        skillImage.addAction(Actions.alpha(1f, cooldown, Interpolation.linear));
    }

    public boolean isEmpty() {
        return this.skill == null;
    }
}
