package com.facundolinlaud.supergame.strategies.skills.epicenter;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.utils.Mappers;

public class NormalSkillEpicenterStrategy implements SkillEpicenterStrategy {
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    @Override
    public Vector2 calculate(Entity caster) {
        PositionComponent casterPosition = pm.get(caster);
        StatusComponent casterStatus = sm.get(caster);

        int xOffset = 0;
        int yOffset = 0;

        switch (casterStatus.getDirection()) {
            case DOWN:
                yOffset = -1;
                break;
            case UP:
                yOffset = 1;
                break;
            case RIGHT:
                xOffset = 1;
                break;
            case LEFT:
                xOffset = -1;
                break;
        }

        return new Vector2(casterPosition.x + xOffset, casterPosition.y + yOffset);
    }
}
