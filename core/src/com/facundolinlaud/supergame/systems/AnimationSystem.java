package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.AnimationComponent;
import com.facundolinlaud.supergame.components.InputComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.helper.AnimationType;
import com.facundolinlaud.supergame.helper.Mappers;

/**
 * Created by facundo on 3/19/16.
 */
public class AnimationSystem extends IteratingSystem {
    private ComponentMapper<InputComponent> im = Mappers.input;
    private ComponentMapper<AnimationComponent> am = Mappers.animation;
    private ComponentMapper<RenderComponent> rm = Mappers.render;

    public AnimationSystem() {
        super(Family.all(AnimationComponent.class, InputComponent.class, RenderComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        InputComponent input = im.get(entity);
        AnimationComponent animation = am.get(entity);
        RenderComponent render = rm.get(entity);

        AnimationType type;
        boolean shouldToggleAnimation = true;

        if(input.direction.x < 0)
            type = AnimationType.LEFT;
        else if(input.direction.x > 0)
            type = AnimationType.RIGHT;
        else if(input.direction.y < 0)
            type = AnimationType.DOWN;
        else if(input.direction.y > 0)
            type = AnimationType.UP;
        else {
            type = AnimationType.STAND;
            shouldToggleAnimation = false;
        }

        animation.toggle(shouldToggleAnimation);
        animation.setCurrentType(type);

        render.texture = animation.getCurrentTextureAndTick(deltaTime);
    }
}
