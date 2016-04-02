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
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.utils.RenderPriority;
import com.facundolinlaud.supergame.utils.WearType;
import com.facundolinlaud.supergame.utils.strategy.impl.SpriteRenderPositionStrategyImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 3/25/16.
 */
public class EntityFactory {
    public static final String PATH_TO_COINS_TEXTURE = "sprites/items/coins.png";
    public static final String PATH_TO_SWORD_TEXTURE = "sprites/items/sword.png";
    public static final String PATH_TO_PLAYER_TEXTURE = "sprites/living/body/light.png";
    public static final String PATH_TO_MAIL_TEXTURE = "sprites/items/armor/mail.png";
    public static final String PATH_TO_METAL_PANTS_TEXTURE = "sprites/items/pants/metal_pants.png";

    public Entity createPlayerWithBody(Body body){
        return new Entity()
                .add(new PositionComponent(20, 50))
                .add(new InputComponent())
                .add(new KeyboardComponent())
                .add(new VelocityComponent(1.9f))
                .add(new BodyComponent(body))
                .add(new WearComponent(createWearables()))
                .add(new RenderComponent(new SpriteRenderPositionStrategyImpl()))
                .add(new AnimationComponent())
                .add(new HealthComponent())
                .add(new BagComponent())
                .add(new AttributesComponent());
    }

    private Map<WearType, Entity> createWearables() {
        HashMap<WearType, Entity> wearables = new HashMap<>();
        wearables.put(WearType.BODY, new Entity().add(new SpriteComponent(PATH_TO_PLAYER_TEXTURE)));
        wearables.put(WearType.CHEST, new Entity().add(new SpriteComponent(PATH_TO_MAIL_TEXTURE)));
        wearables.put(WearType.PANTS, new Entity().add(new SpriteComponent(PATH_TO_METAL_PANTS_TEXTURE)));

        return wearables;
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
