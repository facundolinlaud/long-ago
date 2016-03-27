package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.InputComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.VelocityComponent;
import com.facundolinlaud.supergame.helper.Mappers;

/**
 * Created by facundo on 3/17/16.
 */
public class MovementSystem extends IteratingSystem  {
    private ComponentMapper<VelocityComponent> vm = Mappers.velocity;
    private ComponentMapper<InputComponent> im = Mappers.input;
    private ComponentMapper<BodyComponent> bm = Mappers.body;


    public MovementSystem() {
        super(Family.all(InputComponent.class, PositionComponent.class, VelocityComponent.class, BodyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent velocity = this.vm.get(entity);
        InputComponent input = this.im.get(entity);
        BodyComponent body = this.bm.get(entity);

        body.body.setLinearVelocity(velocity.velocity * input.direction.x * (deltaTime + 1), velocity.velocity * input.direction.y * (deltaTime + 1));
    }
}