package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.facundolinlaud.supergame.components.InputComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.VelocityComponent;
import com.facundolinlaud.supergame.helper.InputHelper;
import com.facundolinlaud.supergame.helper.Mappers;

/**
 * Created by facundo on 3/17/16.
 */
public class MovementSystem extends IteratingSystem  {
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<VelocityComponent> vm = Mappers.velocity;
    private ComponentMapper<InputComponent> im = Mappers.input;


    public MovementSystem() {
        super(Family.all(InputComponent.class, PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position= this.pm.get(entity);
        VelocityComponent velocity = this.vm.get(entity);
        InputComponent input = this.im.get(entity);

        position.x += input.direction.x * (velocity.velocity * (deltaTime + 1));
        position.y += input.direction.y * (velocity.velocity * (deltaTime + 1));
    }
}
