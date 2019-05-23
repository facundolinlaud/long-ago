package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.builder.ItemBuilder;
import com.facundolinlaud.supergame.model.RenderPriority;
import com.facundolinlaud.supergame.model.item.EquipmentInformation;
import com.facundolinlaud.supergame.model.item.Item;

import java.util.Map;

/**
 * Created by facundo on 27/7/16.
 */
public class ItemFactory {
    public static final int LEATHER_ARMOR = 0;
    public static final int MAIL_ARMOR = 1;
    public static final int LEATHER_PLATE = 2;
    public static final int FULL_PLATE_GLOVES = 3;
    public static final int LEATHER_BELT = 4;
    public static final int LEATHER_BOOTS = 5;
    public static final int METAL_BOOTS = 6;
    public static final int PLATE_GLOVES = 7;
    public static final int HOOD = 8;
    public static final int PLATE_PANTS = 9;
    public static final int DAGGER = 10;
    public static final int SABER = 11;
    public static final int SPEAR = 12;
    public static final int COINS = 13;

    private Map<Integer, Item> models;

    public ItemFactory() {
        this.models = ModelFactory.getItemsModel();
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
}
