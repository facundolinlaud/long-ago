package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.model.Action;
import com.facundolinlaud.supergame.model.Direction;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/20/16.
 */
public class KeyboardSystem extends EntitySystem {
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private ImmutableArray<Entity> entities;

    public KeyboardSystem() {}

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(StatusComponent.class, KeyboardComponent.class).get());
    }

    public void update(float deltaTime) {
        boolean gathering = Gdx.input.isKeyJustPressed(Input.Keys.E);

        for(Entity entity : entities){
            StatusComponent status = sm.get(entity);

            Direction newDirection = resolveDirection();

            if(newDirection != null){
                status.direction = newDirection;
                status.action = Action.WALKING;
            }else{
                status.action = Action.STANDING;
            }

            status.gathering = gathering;
        }
    }

    private Direction resolveDirection(){
        boolean W = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean A = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean S = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean D = Gdx.input.isKeyPressed(Input.Keys.D);

        if(A)
            return Direction.LEFT;
        else if(D)
            return Direction.RIGHT;
        else if(W)
            return Direction.UP;
        else if(S)
            return Direction.DOWN;
        else return null;
    }
}