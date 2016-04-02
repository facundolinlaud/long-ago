package com.facundolinlaud.supergame.ui.controller.impl;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.ui.controller.EquipmentUIController;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.EquipmentUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.WearType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 4/2/16.
 */
public class EquipmentUIControllerImpl implements EquipmentUIController {
    private ComponentMapper<ItemComponent> itc = Mappers.item;
    private ComponentMapper<RenderComponent> rm = Mappers.render;

    private EquipmentUI ui;
    private Entity equipper;

    public EquipmentUIControllerImpl(EquipmentUI ui) {
        this.ui = ui;
    }

    @Override
    public void setEquipper(Entity player) {
        this.equipper = player;
    }

    @Override
    public void update(Entity entity, WearComponent wear) {
        setEquipper(entity);
        Map<WearType, Entity> wearables = wear.wearables;
        Map<WearType, Item> items = new HashMap<>();

        for(WearType wearType : wearables.keySet()){
            Entity e = wearables.get(wearType);

            ItemComponent itemComponent = itc.get(e);
            if(itemComponent == null) continue;

            items.put(wearType, new Item(itemComponent.name, itemComponent.weight, itemComponent.picture));
        }

        ui.update(items);
    }
}
