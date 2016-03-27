package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.InputComponent;
import com.facundolinlaud.supergame.components.KeyboardComponent;
import com.facundolinlaud.supergame.utils.InputType;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/20/16.
 */
public class KeyboardSystem extends EntitySystem {
    private ComponentMapper<InputComponent> im = Mappers.input;

    private ImmutableArray<Entity> entities;

    public KeyboardSystem() {}

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(InputComponent.class, KeyboardComponent.class).get());
    }

    public void update(float deltaTime) {
        Vector2 resolvedDirection = resolveDirection();
        boolean gathering = Gdx.input.isKeyJustPressed(Input.Keys.E);

        for(Entity entity : entities){
            InputComponent input = im.get(entity);
            input.direction = resolvedDirection;
            input.gathering = gathering;
        }
    }

    private Vector2 resolveDirection(){
        boolean W = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean A = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean S = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean D = Gdx.input.isKeyPressed(Input.Keys.D);

        int x, y;

        if(A)
            x = InputType.LEFT;
        else if(D)
            x = InputType.RIGHT;
        else
            x = InputType.NONE;

        if(W)
            y = InputType.UP;
        else if(S)
            y = InputType.DOWN;
        else
            y = InputType.NONE;


        return new Vector2(x, y);
    }
}