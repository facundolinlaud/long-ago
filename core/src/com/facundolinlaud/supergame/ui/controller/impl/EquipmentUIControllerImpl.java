package com.facundolinlaud.supergame.ui.controller.impl;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.sprite.RefreshSpriteRequirementComponent;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.ui.controller.EquipmentUIController;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.model.equipment.Equipable;
import com.facundolinlaud.supergame.ui.view.EquipmentUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.utils.events.EquipItemEvent;
import com.facundolinlaud.supergame.utils.events.Event;
import com.facundolinlaud.supergame.utils.events.UnequipItemEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 4/2/16.
 */
public class EquipmentUIControllerImpl implements EquipmentUIController {
    private ComponentMapper<BagComponent> bm = Mappers.bag;
    private ComponentMapper<ItemComponent> im = Mappers.item;
    private ComponentMapper<WearComponent> wm = Mappers.wear;
    private ComponentMapper<EquipableComponent> em = Mappers.equipable;

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
        Map<EquipSlot, Entity> wearables = wear.wearables;
        Map<EquipSlot, Item> items = new HashMap<>();

        for(EquipSlot equipSlot : wearables.keySet()){
            Entity e = wearables.get(equipSlot);

            ItemComponent item = im.get(e);
            if(item == null) continue;

            EquipableComponent equipable = em.get(e);

            /* fijate que por ahi el wear type deberia estar dentro de equipable component y no en wear component */
            items.put(equipSlot,
                    new Item(item.name, item.picture,
                    new Equipable(equipSlot, equipable.attack, equipable.defense)));
        }

        ui.update(items);
    }

    @Override
    public void handle(Event event) {
        if(event instanceof UnequipItemEvent){
            onUnequipItemEvent((UnequipItemEvent) event);
        }else if(event instanceof EquipItemEvent){
            onEquipItemEvent((EquipItemEvent) event);
        }
    }

    private void onUnequipItemEvent(UnequipItemEvent event){
        Item item = event.getItem();

        WearComponent wearComponent = wm.get(equipper);
        Entity itemEntity = wearComponent.wearables.remove(item.getEquipable().getEquipSlot());
        BagComponent bagComponent = bm.get(equipper);
        bagComponent.addItem(itemEntity);

        refreshEquipperSprite();

        System.out.println(item.getEquipable() + " unequipped");
    }

    private void onEquipItemEvent(EquipItemEvent event) {
        Item item = event.getItem();

        BagComponent bagComponent = bm.get(equipper);
        Entity itemEntity = bagComponent.items.remove(item.getInvented().getPositionInBag());
        WearComponent wearComponent = wm.get(equipper);
        wearComponent.wearables.put(item.getEquipable().getEquipSlot(), itemEntity);

        refreshEquipperSprite();

        System.out.println(item.getEquipable() + " equipped");
    }

    private void refreshEquipperSprite() {
        equipper.add(new RefreshSpriteRequirementComponent());
    }
}
