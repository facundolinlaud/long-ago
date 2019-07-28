package com.facundolinlaud.supergame.utils.events;

import com.facundolinlaud.supergame.model.skill.Skill;

public class SkillEquippedEvent {
    private Skill skill;
    private int index;

    public SkillEquippedEvent(Skill skill, int index) {
        this.skill = skill;
        this.index = index;
    }

    public Skill getSkill() {
        return skill;
    }

    public int getIndex() {
        return index;
    }
}
