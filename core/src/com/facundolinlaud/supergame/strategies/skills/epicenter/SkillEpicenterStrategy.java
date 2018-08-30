package com.facundolinlaud.supergame.strategies.skills.epicenter;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public interface SkillEpicenterStrategy {
    Vector2 calculate(Entity caster);
}
