package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;

public class SkillCastingRequestComponent implements Component {
    public int requestedSkillId;

    public SkillCastingRequestComponent(int requestedSkillId) {
        this.requestedSkillId = requestedSkillId;
    }
}
