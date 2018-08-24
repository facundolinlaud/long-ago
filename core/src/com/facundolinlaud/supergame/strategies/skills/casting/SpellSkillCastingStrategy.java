package com.facundolinlaud.supergame.strategies.skills.casting;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.skills.epicenter.SpellSkillEpicenterStrategy;
import com.facundolinlaud.supergame.systems.skills.logic.SkillCastedProsecutor;

public class SpellSkillCastingStrategy implements SkillCastingStrategy {
    private SkillCastedProsecutor skillCastedProsecutor;

    public SpellSkillCastingStrategy(Engine engine) {
        this.skillCastedProsecutor = new SkillCastedProsecutor(engine, new SpellSkillEpicenterStrategy());
    }

    @Override
    public void executeSkillEffects(Entity caster, Skill skill) {
        this.skillCastedProsecutor.execute(caster, skill);
    }
}
