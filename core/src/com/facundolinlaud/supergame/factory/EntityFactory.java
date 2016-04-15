package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
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
    public static final String PATH_TO_COINS_PICTURE = "pictures/items/others/coins.png";
    public static final String PATH_TO_SWORD_PICTURE = "pictures/items/weapons/sword.png";
    public static final String PATH_TO_METAL_BOOTS_PICTURE = "pictures/items/boots/metal_boots.png";
    public static final String PATH_TO_MAIL_PICTURE = "pictures/items/armor/chain.png";

    public static final String PATH_TO_PLAYER_SPRITE = "sprites/living/body/light.png";
    public static final String PATH_TO_MAIL_SPRITE = "sprites/items/armor/mail.png";
    public static final String PATH_TO_METAL_BOOTS_SPRITE = "sprites/items/boots/metal_boots.png";

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
        wearables.put(WearType.BODY, new Entity().add(new SpriteComponent(PATH_TO_PLAYER_SPRITE)));
        addEquipable(wearables, WearType.CHEST, "Mail armor", PATH_TO_MAIL_SPRITE, PATH_TO_MAIL_PICTURE);
        addEquipable(wearables, WearType.SHOES, "Plate boots", PATH_TO_METAL_BOOTS_SPRITE, PATH_TO_METAL_BOOTS_PICTURE);

        return wearables;
    }

    private void addEquipable(Map<WearType, Entity> wearables, WearType wearType, String name, String texturePath, String picture){
        wearables.put(wearType, new Entity()
                .add(new EquipableComponent(wearType, 4, 5))
                .add(new ItemComponent(name, picture))
                .add(new SpriteComponent(texturePath)));
    }

    private Entity createBaseItemEntity(String texturePath, int x, int y){
        return new Entity()
                .add(new PositionComponent(x, y))
                .add(new RenderComponent(new TextureRegion(new Texture(texturePath)), new RenderPriority(1)))
                .add(new PickupableComponent())
                .add(new ItemComponent(new Texture(texturePath)));
    }

    public Entity createCoinsItemWithBody(Body body) {
        return createBaseItemEntity(PATH_TO_COINS_PICTURE, 21, 45)
                .add(new BodyComponent(body));
    }

    public Entity createWordItemWithBody(Body body) {
        return createBaseItemEntity(PATH_TO_SWORD_PICTURE, 21, 45)
                .add(new BodyComponent(body));
    }
}
