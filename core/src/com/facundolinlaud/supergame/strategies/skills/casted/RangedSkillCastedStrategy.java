package com.facundolinlaud.supergame.strategies.skills.casted;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.RangedSkill;

public class RangedSkillCastedStrategy implements SkillCastedStrategy<RangedSkill> {
    private Engine engine;

    public RangedSkillCastedStrategy(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(Entity caster, RangedSkill skill) {

    }
}
