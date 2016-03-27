package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.utils.AnimationType;
import com.facundolinlaud.supergame.utils.RenderPriority;

import java.util.Arrays;

/**
 * Created by facundo on 3/25/16.
 */
public class EntityFactory {
    public static final String PATH_TO_PLAYER_TEXTURE = "sprites/main_player.png";
    public static final String PATH_TO_COINS_TEXTURE = "sprites/items/coins.png";

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
                        new Texture(PATH_TO_PLAYER_TEXTURE)))
                .add(new HealthComponent())
                .add(new BagComponent());
    }

    public Entity createItemWithBody(Body body) {
        return new Entity()
                .add(new PositionComponent(20, 45))
                .add(new RenderComponent(new TextureRegion(new Texture(PATH_TO_COINS_TEXTURE)), new RenderPriority(1)))
                .add(new PickupableComponent())
                .add(new ItemComponent())
                .add(new BodyComponent(body));
    }
}
