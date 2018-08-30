package com.facundolinlaud.supergame.strategies.skills.epicenter;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.utils.Mappers;

public class SpellSkillEpicenterStrategy implements SkillEpicenterStrategy {
    private ComponentMapper<SkillClickComponent> scm = Mappers.skillClick;

    @Override
    public Vector2 calculate(Entity caster) {
        SkillClickComponent clickComponent = scm.get(caster);
        caster.remove(SkillClickComponent.class);

        return clickComponent.getClickedPosition();
    }
}
