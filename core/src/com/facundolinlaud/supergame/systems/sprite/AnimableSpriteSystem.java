package com.facundolinlaud.supergame.systems.sprite;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.sprite.AnimableSpriteComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.model.status.Status;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 26/7/16.
 */
public class AnimableSpriteSystem extends IteratingSystem {
    private ComponentMapper<AnimableSpriteComponent> asm = Mappers.animableSprite;
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<RenderComponent> rm = Mappers.render;

    private Map<Entity, Status> lastEntitiesStatus;

    public AnimableSpriteSystem() {
        super(Family.all(StatusComponent.class, AnimableSpriteComponent.class, RenderComponent.class).get());
        this.lastEntitiesStatus = new HashMap<>();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimableSpriteComponent animableSpriteComponent = asm.get(entity);
        StatusComponent statusComponent = sm.get(entity);

        Action action = statusComponent.getAction();
        Direction direction = statusComponent.getDirection();

        putNextFrameOnRenderComponent(entity, action, direction, animableSpriteComponent);
        tickOrResetStateTime(deltaTime, animableSpriteComponent, statusComponent);
    }

    private void putNextFrameOnRenderComponent(Entity e, Action a, Direction d, AnimableSpriteComponent animableSpriteComponent) {
        Status status = new Status(a, d);
        RenderComponent renderComponent = rm.get(e);
        Animation animation = animableSpriteComponent.animations.get(status);
        renderComponent.texture = (TextureRegion) animation.getKeyFrame(animableSpriteComponent.stateTime);
    }

    /* TODO: see if I can make this prettier */
    private void tickOrResetStateTime(float deltaTime, AnimableSpriteComponent animableSpriteComponent, StatusComponent statusComponent) {
        if(statusComponent.isChangeInActionOrDirection()){
            animableSpriteComponent.stateTime = 0f;
            statusComponent.setChangeInActionOrDirection(false);
        }else{
            animableSpriteComponent.stateTime += deltaTime;
        }
    }
}
