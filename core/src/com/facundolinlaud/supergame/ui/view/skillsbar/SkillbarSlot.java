package com.facundolinlaud.supergame.ui.view.skillsbar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.cross.Slot;
import com.facundolinlaud.supergame.ui.view.inventory.NullItemSlot;

public class SkillbarSlot extends Slot<Skill> {
    private static final int HEIGHT = 32;
    private static final int WIDTH = 32;

    private Skill skill;
    private Image skillImage;
    private NullItemSlot nullSkillSlot;
    private Image cooldownImage;

    public SkillbarSlot(Skin skin) {
        this.nullSkillSlot = new NullItemSlot(skin);
        this.cooldownImage = new Image();
        this.cooldownImage.setColor(Color.BLACK);
        this.cooldownImage.setSize(WIDTH, HEIGHT);
        this.cooldownImage.setPosition(nullSkillSlot.getX(), nullSkillSlot.getY());

        setSize(WIDTH, HEIGHT);
        add(nullSkillSlot);
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
