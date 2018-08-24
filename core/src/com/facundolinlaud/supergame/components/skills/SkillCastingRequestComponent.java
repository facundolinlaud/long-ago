package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.skill.Skill;

public class SkillCastingRequestComponent implements Component {
    public Skill requestedSkill;

    public SkillCastingRequestComponent(Skill requestedSkill) {
        this.requestedSkill = requestedSkill;
    }

    public Skill getRequestedSkill() {
        return requestedSkill;
    }
}
