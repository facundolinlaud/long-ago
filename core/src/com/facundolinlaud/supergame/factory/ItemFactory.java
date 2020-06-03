package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.builder.ItemBuilder;
import com.facundolinlaud.supergame.model.item.EquipmentInformation;
import com.facundolinlaud.supergame.model.item.Item;

import java.util.Map;

/**
 * Created by facundo on 27/7/16.
 */
public class ItemFactory {
    public static final String SABER = "saber";
    public static final String COINS = "coins";

    private Map<String, Item> models;

    public ItemFactory() {
        this.models = ModelFactory.getItemsModel();
    }

    public ItemBuilder getItem(String id) {
        Item model = models.get(id);

        ItemBuilder itemBuilder = new ItemBuilder(id, model)
                .pickupable()
                .withRender(model.getPicture());

        if(model.isEquipable()){
            EquipmentInformation eq = model.getEquipmentInformation();
            itemBuilder.equipable(model.getSprite(), eq);
        }

        return itemBuilder;
    }
}
