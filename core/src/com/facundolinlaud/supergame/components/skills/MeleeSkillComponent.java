package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.skill.MeleeSkill;

public class MeleeSkillComponent implements Component {
    public MeleeSkill meleeSkill;

    public MeleeSkillComponent(MeleeSkill meleeSkill) {
        this.meleeSkill = meleeSkill;
    }
}
