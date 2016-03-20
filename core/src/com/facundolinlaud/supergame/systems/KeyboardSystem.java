package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.InputComponent;
import com.facundolinlaud.supergame.components.KeyboardComponent;
import com.facundolinlaud.supergame.helper.InputHelper;
import com.facundolinlaud.supergame.helper.Mappers;

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
        boolean W = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean A = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean S = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean D = Gdx.input.isKeyPressed(Input.Keys.D);

        int x, y;

        if(A)
            x = InputHelper.LEFT;
        else if(D)
            x = InputHelper.RIGHT;
        else
            x = InputHelper.NONE;

        if(W)
            y = InputHelper.UP;
        else if(S)
            y = InputHelper.DOWN;
        else
            y = InputHelper.NONE;


        for(Entity entity : entities){
            InputComponent input = im.get(entity);
            input.direction = new Vector2(x, y);
        }
    }
}