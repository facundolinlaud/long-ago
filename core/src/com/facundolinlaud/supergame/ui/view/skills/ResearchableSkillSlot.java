package com.facundolinlaud.supergame.ui.view.skills;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.cross.SkillSlot;

public class ResearchableSkillSlot extends ImageButton {
    private SkillSlot skillSlot;

    public ResearchableSkillSlot(Skin skin) {
        super(skin, "skill-slot");
        this.setSize(42, 42);
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
}
