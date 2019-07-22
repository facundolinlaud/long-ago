package com.facundolinlaud.supergame.ui.view.skilltree;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.cross.SkillSlot;

public class SkillTreeFramedSlot extends ImageButton {
    private SkillSlot skillSlot;

    public SkillTreeFramedSlot(Skin skin) {
        super(skin, "skill-slot");
        this.setBackground("gothic-slot-skill");
        this.skillSlot = new SkillSlot(skin);
        add(skillSlot).align(Align.center).size(32, 32);
    }

    public Skill getContent() {
        return skillSlot.getContent();
    }

    public void setContent(Skill skill) {
        skillSlot.setContent(skill);
    }

    public void clearContent() {
        skillSlot.clearContent();
    }

    public SkillSlot getSlot(){
        return this.skillSlot;
    }
}
