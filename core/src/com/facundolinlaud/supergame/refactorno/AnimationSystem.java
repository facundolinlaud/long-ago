package com.facundolinlaud.supergame.refactorno;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/31/16.
 */
public class AnimationSystem extends IteratingSystem {
    private ComponentMapper<AnimationComponent> am = Mappers.animation;
    private ComponentMapper<RenderComponent> rm = Mappers.render;
    private ComponentMapper<InputComponent> im = Mappers.input;

    public AnimationSystem() {
        super(Family.all(AnimationComponent.class, WearComponent.class, InputComponent.class, RenderComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animation = am.get(entity);
        RenderComponent render = rm.get(entity);
        InputComponent input = im.get(entity);

        boolean shouldAnimate = true;

        if(input.direction.x > 0){
            animation.setCurrentType(AnimationType.WALKING_RIGHT);
        }else if(input.direction.x < 0){
            animation.setCurrentType(AnimationType.WALKING_LEFT);
        }else if(input.direction.y > 0){
            animation.setCurrentType(AnimationType.WALKING_UP);
        }else if(input.direction.y < 0){
            animation.setCurrentType(AnimationType.WALKING_DOWN);
        }else{
            shouldAnimate = false;
        }

        animation.animate(shouldAnimate);
        render.texture = animation.getCurrentTextureAndTick(deltaTime);
    }
}
