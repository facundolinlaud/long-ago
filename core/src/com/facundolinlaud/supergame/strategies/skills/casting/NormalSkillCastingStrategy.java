package com.facundolinlaud.supergame.strategies.skills.casting;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.skills.epicenter.NormalSkillEpicenterStrategy;
import com.facundolinlaud.supergame.systems.skills.logic.DefaultSkillCastedProsecutor;

public class NormalSkillCastingStrategy implements SkillCastingStrategy {
    private DefaultSkillCastedProsecutor skillCastedProsecutor;

    public NormalSkillCastingStrategy(Engine engine, ParticleFactory particleFactory, LightsManager lightsManager) {
        this.skillCastedProsecutor = new DefaultSkillCastedProsecutor(engine,
                new NormalSkillEpicenterStrategy(), particleFactory, lightsManager);
    }

    @Override
    public void executeSkillEffects(Entity caster, Skill skill) {
        this.skillCastedProsecutor.execute(caster, skill);
    }
}
