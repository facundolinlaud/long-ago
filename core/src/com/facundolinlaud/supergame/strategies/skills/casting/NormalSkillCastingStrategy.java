package com.facundolinlaud.supergame.strategies.skills.casting;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.skills.epicenter.NormalSkillEpicenterStrategy;
import com.facundolinlaud.supergame.systems.skills.logic.SkillCastedProsecutor;

public class NormalSkillCastingStrategy implements SkillCastingStrategy {
    private SkillCastedProsecutor skillCastedProsecutor;

    public NormalSkillCastingStrategy(Engine engine) {
        this.skillCastedProsecutor = new SkillCastedProsecutor(engine, new NormalSkillEpicenterStrategy());
    }

    @Override
    public void executeSkillEffects(Entity caster, Skill skill) {
        this.skillCastedProsecutor.execute(caster, skill);
    }
}
