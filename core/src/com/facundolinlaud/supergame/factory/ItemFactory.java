package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.facundolinlaud.supergame.builder.ItemBuilder;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.items.PickupableComponent;
import com.facundolinlaud.supergame.components.sprite.StackableSpriteComponent;
import com.facundolinlaud.supergame.model.RenderPriority;
import com.facundolinlaud.supergame.model.item.EquipmentInformation;
import com.facundolinlaud.supergame.model.item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 27/7/16.
 */
public class ItemFactory {
    public static final String PATH_TO_COINS_PICTURE = "pictures/items/others/coins.png";
    public static final String PATH_TO_SWORD_PICTURE = "pictures/items/weapon/saber.png";

    private Map<Integer, Item> models;

    public ItemFactory() {
        this.models = ModelFactory.getItemsModel().getItems();
    }

    public ItemBuilder getItem(Integer id) {
        Item model = models.get(id);

        ItemBuilder itemBuilder = new ItemBuilder(model.getName(), model.getPicture())
                .pickupable()
                .withRender(model.getPicture(), new RenderPriority(1));

        if(model.isEquipable()){
            EquipmentInformation eq = model.getEquipmentInformation();
            itemBuilder.equipable(model.getTexture(), eq);
        }

        return itemBuilder;
    }

    private static Entity createBaseItemEntity(String texturePath, int x, int y){
        return new Entity()
                .add(new PositionComponent(x, y))
                .add(new RenderComponent(new TextureRegion(TextureFactory.getTexture(texturePath)), new RenderPriority(1)))
                .add(new PickupableComponent())
                .add(new ItemComponent(TextureFactory.getTexture(texturePath)));
    }

    public static Entity createCoins() {
        return createBaseItemEntity(PATH_TO_COINS_PICTURE, 21, 48);
    }

    public static Entity createSword() {
        return createBaseItemEntity(PATH_TO_SWORD_PICTURE, 20, 48);
    }
}
