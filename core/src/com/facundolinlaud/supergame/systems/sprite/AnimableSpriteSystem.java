package com.facundolinlaud.supergame.systems.sprite;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.sprite.AnimableSpriteComponent;
import com.facundolinlaud.supergame.model.Status;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 26/7/16.
 */
public class AnimableSpriteSystem extends IteratingSystem {
    private ComponentMapper<AnimableSpriteComponent> asm = Mappers.animableSprite;
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<RenderComponent> rm = Mappers.render;

    public AnimableSpriteSystem() {
        super(Family.all(StatusComponent.class, AnimableSpriteComponent.class, RenderComponent.class).get());
    }

    private float stateTime = 0;

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimableSpriteComponent animableSpriteComponent = asm.get(entity);
        RenderComponent renderComponent = rm.get(entity);
        StatusComponent statusComponent = sm.get(entity);

        Status status = new Status(statusComponent.action, statusComponent.direction);

        Animation animation = animableSpriteComponent.animations.get(status);
        renderComponent.texture = animation.getKeyFrame(stateTime);
        stateTime += deltaTime;
    }
}
