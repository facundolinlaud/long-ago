package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.skill.RangedSkill;

public class RangedSkillComponent implements Component {
    public RangedSkill rangedSkill;

    public RangedSkillComponent(RangedSkill rangedSkill) {
        this.rangedSkill = rangedSkill;
    }
}
