package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.items.PickupableComponent;
import com.facundolinlaud.supergame.model.RenderPriority;

/**
 * Created by facundo on 27/7/16.
 */
public class ItemFactory {
    public static final String PATH_TO_COINS_PICTURE = "pictures/items/others/coins.png";
    public static final String PATH_TO_SWORD_PICTURE = "pictures/items/weapon/saber.png";

    private static PhysicsFactory physicsFactory = PhysicsFactory.get();

    private static Entity createBaseItemEntity(String texturePath, int x, int y){
        return new Entity()
                .add(new PositionComponent(x, y))
                .add(new RenderComponent(new TextureRegion(TextureFactory.getTexture(texturePath)), new RenderPriority(1)))
                .add(new PickupableComponent())
                .add(new ItemComponent(TextureFactory.getTexture(texturePath)));
    }

    public static Entity createCoins() {
        return createBaseItemEntity(PATH_TO_COINS_PICTURE, 21, 48)
                .add(new BodyComponent(physicsFactory.createItemBody()));
    }

    public static Entity createSword() {
        return createBaseItemEntity(PATH_TO_SWORD_PICTURE, 20, 48)
                .add(new BodyComponent(physicsFactory.createItemBody()));
    }
}
