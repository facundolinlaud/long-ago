package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.InputComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.SpriteComponent;
import com.facundolinlaud.supergame.utils.AnimationType2;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/31/16.
 */
public class AnimationSystem extends IteratingSystem {
    private ComponentMapper<InputComponent> im = Mappers.input;
    private ComponentMapper<SpriteComponent> sm = Mappers.sprite;
    private ComponentMapper<RenderComponent> rm = Mappers.render;

    public AnimationSystem() {
        super(Family.all(SpriteComponent.class, InputComponent.class, RenderComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        InputComponent input = im.get(entity);
        SpriteComponent sprite  = sm.get(entity);
        RenderComponent render = rm.get(entity);

        boolean shouldAnimate = true;

        if(input.direction.x > 0){
            sprite.setCurrentType(AnimationType2.WALKING_RIGHT);
        }else if(input.direction.x < 0){
            sprite.setCurrentType(AnimationType2.WALKING_LEFT);
        }else if(input.direction.y > 0){
            sprite.setCurrentType(AnimationType2.WALKING_UP);
        }else if(input.direction.y < 0){
            sprite.setCurrentType(AnimationType2.WALKING_DOWN);
        }else{
            shouldAnimate = false;
        }

        sprite.animate(shouldAnimate);
        render.texture = sprite.getCurrentTextureAndTick(deltaTime);
    }
}
