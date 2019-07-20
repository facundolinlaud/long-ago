package com.facundolinlaud.supergame.utils.events;

import com.facundolinlaud.supergame.model.skill.Skill;

import java.util.List;

public class SkillBarChangedEvent extends Event {
    private List<Skill> skills;

    public SkillBarChangedEvent(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Skill> getSkills() {
        return skills;
    }
}
