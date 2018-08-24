package com.facundolinlaud.supergame.strategies.skills.casted;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.skill.Skill;

public class ProjectileCastedStrategy implements SkillCastedStrategy {
    private Engine engine;

    public ProjectileCastedStrategy(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(Entity caster, Skill skill) {

    }
}
