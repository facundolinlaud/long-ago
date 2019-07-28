package com.facundolinlaud.supergame.ui.view.cross.draganddrop;

import com.facundolinlaud.supergame.model.skill.Skill;

public class SkillDragInformation {
    private int index;
    private Skill skill;

    public SkillDragInformation(int index, Skill skill) {
        this.index = index;
        this.skill = skill;
    }

    public int getIndex() {
        return index;
    }

    public Skill getSkill() {
        return skill;
    }
}
