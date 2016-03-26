package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.helper.AnimationType;

import java.util.Arrays;

/**
 * Created by facundo on 3/25/16.
 */
public class EntityFactory {
    public static final String PATH_TO_PLAYER_SPRITE = "sprites/main_player.png";

    public Entity createPlayerWithBody(Body body){
        return new Entity()
                .add(new PositionComponent(20, 50))
                .add(new InputComponent())
                .add(new KeyboardComponent())
                .add(new VelocityComponent(1.9f))
                .add(new RenderComponent())
                .add(new BodyComponent(body))
                .add(new AnimationComponent(Arrays.asList(
                        AnimationType.DOWN, AnimationType.LEFT, AnimationType.RIGHT, AnimationType.UP),
                        new Texture(PATH_TO_PLAYER_SPRITE)))
                .add(new HealthComponent());
    }
}
