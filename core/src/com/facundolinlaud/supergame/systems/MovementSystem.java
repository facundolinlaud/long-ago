package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.VelocityComponent;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/17/16.
 */
public class MovementSystem extends IteratingSystem  {
    private ComponentMapper<VelocityComponent> vm = Mappers.velocity;
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<BodyComponent> bm = Mappers.body;


    public MovementSystem() {
        super(Family.all(StatusComponent.class, PositionComponent.class, VelocityComponent.class, BodyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent velocity = vm.get(entity);
        StatusComponent status = sm.get(entity);
        BodyComponent body = bm.get(entity);

        int toggleX = 0;
        int toggleY = 0;

        if(Action.WALKING.equals(status.getAction())) {
            switch (status.getDirection()) {
                case UP:
                    toggleY = 1;
                    break;
                case DOWN:
                    toggleY = -1;
                    break;
                case LEFT:
                    toggleX = -1;
                    break;
                case RIGHT:
                    toggleX = 1;
                    break;
            }
        }

        body.body.setLinearVelocity(velocity.velocity * toggleX * (deltaTime + 1), velocity.velocity * toggleY * (deltaTime + 1));
    }
}