package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.skill.SpellSkill;

public class SpellSkillComponent implements Component {
    public SpellSkill spellSkill;

    public SpellSkillComponent(SpellSkill spellSkill) {
        this.spellSkill = spellSkill;
    }
}
