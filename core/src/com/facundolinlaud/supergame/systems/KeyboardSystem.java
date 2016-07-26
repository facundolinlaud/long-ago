package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.refactor.Action;
import com.facundolinlaud.supergame.refactor.Direction;
import com.facundolinlaud.supergame.refactor.StatusComponent;
import com.facundolinlaud.supergame.refactorno.InputComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.refactorno.InputType;
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
        boolean W = Gdx.input.isKeyJustPressed(Input.Keys.W);
        boolean A = Gdx.input.isKeyJustPressed(Input.Keys.A);
        boolean S = Gdx.input.isKeyJustPressed(Input.Keys.S);
        boolean D = Gdx.input.isKeyJustPressed(Input.Keys.D);

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