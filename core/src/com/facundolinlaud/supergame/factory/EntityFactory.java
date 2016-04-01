package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.items.PickupableComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.utils.AnimationType;
import com.facundolinlaud.supergame.utils.RenderPriority;
import com.facundolinlaud.supergame.utils.strategy.impl.SpriteRenderPositionStrategyImpl;

import java.util.Arrays;

/**
 * Created by facundo on 3/25/16.
 */
public class EntityFactory {
    public static final String PATH_TO_PLAYER_TEXTURE = "sprites/main_player.png";
    public static final String PATH_TO_COINS_TEXTURE = "sprites/items/coins.png";
    public static final String PATH_TO_SWORD_TEXTURE = "sprites/items/sword.png";
    public static final String PATH_TO_PLAYER_TEXTURE_2 = "sprites/living/body/light.png";

    public Entity createPlayerWithBody(Body body){
        return new Entity()
                .add(new PositionComponent(20, 50))
                .add(new InputComponent())
                .add(new KeyboardComponent())
                .add(new VelocityComponent(1.9f))
                .add(new RenderComponent(new SpriteRenderPositionStrategyImpl()))
                .add(new BodyComponent(body))
                .add(new SpriteComponent(new Texture(PATH_TO_PLAYER_TEXTURE_2)))
                .add(new HealthComponent())
                .add(new BagComponent())
                .add(new AttributesComponent());
    }

    private Entity createBaseItemEntity(String texturePath, int x, int y){
        return new Entity()
                .add(new PositionComponent(x, y))
                .add(new RenderComponent(new TextureRegion(new Texture(texturePath)), new RenderPriority(1)))
                .add(new PickupableComponent())
                .add(new ItemComponent());
    }

    public Entity createCoinsItemWithBody(Body body) {
        return createBaseItemEntity(PATH_TO_COINS_TEXTURE, 21, 45)
                .add(new BodyComponent(body));
    }

    public Entity createWordItemWithBody(Body body) {
        return createBaseItemEntity(PATH_TO_SWORD_TEXTURE, 21, 45)
                .add(new BodyComponent(body));
    }
}
