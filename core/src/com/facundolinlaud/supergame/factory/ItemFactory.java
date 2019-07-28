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
    public static final int SABER = 11;
    public static final int COINS = 13;

    private Map<Integer, Item> models;

    public ItemFactory() {
        this.models = ModelFactory.getItemsModel();
    }

    public ItemBuilder getItem(Integer id) {
        Item model = models.get(id);

        ItemBuilder itemBuilder = new ItemBuilder(model)
                .pickupable()
                .withRender(model.getPicture());

        if(model.isEquipable()){
            EquipmentInformation eq = model.getEquipmentInformation();
            itemBuilder.equipable(model.getTexture(), eq);
        }

        return itemBuilder;
    }
}
