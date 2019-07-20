package com.facundolinlaud.supergame.ui.view.cross;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.facundolinlaud.supergame.factory.TextureFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

public class SkillSlot extends Slot<Skill> {
    private Skill skill;
    private Image skillImage;
    private ImageButton slotButton;

    public SkillSlot(Skin skin) {
        this.slotButton = new ImageButton(skin, Themes.ImageButton.SLOT);
        this.skillImage = new Image();

        add(slotButton);
        add(skillImage);
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

    protected Image getSkillImage() {
        return skillImage;
    }

    protected ImageButton getFrame(){
        return slotButton;
    }
}
