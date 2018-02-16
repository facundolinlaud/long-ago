package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;

public class SkillCastRequestComponent implements Component {
    public int skillId;

    public SkillCastRequestComponent(int skillId) {
        this.skillId = skillId;
    }
}
