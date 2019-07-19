package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.components.sprite.RefreshSpriteRequirementComponent;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.model.equipment.Equipable;
import com.facundolinlaud.supergame.ui.view.EquipmentUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.Messages;
import com.facundolinlaud.supergame.utils.events.EquipItemEvent;
import com.facundolinlaud.supergame.utils.events.UnequipItemEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 4/2/16.
 */
public class EquipmentUIController implements Telegraph {
    private ComponentMapper<BagComponent> bm = Mappers.bag;
    private ComponentMapper<ItemComponent> im = Mappers.item;
    private ComponentMapper<WearComponent> wm = Mappers.wear;
    private ComponentMapper<EquipableComponent> em = Mappers.equipable;

    private EquipmentUI ui;
    private Entity player;

    public EquipmentUIController(EquipmentUI ui, Entity player) {
        this.ui = ui;
        this.player = player;

        refreshEquipment();
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        switch(msg.message){
            case Messages.ITEM_UNEQUIPPED:
                onUnequipItemEvent((UnequipItemEvent) msg.extraInfo);
                break;
            case Messages.ITEM_EQUIPPED:
                onEquipItemEvent((EquipItemEvent) msg.extraInfo);
                break;
            case Messages.EQUIPMENT_CHANGED:
                refreshEquipment();
                break;
        }

        return false;
    }

    public void refreshEquipment() {
        WearComponent wear = wm.get(player);
        Map<EquipSlot, Entity> wearables = wear.wearables;
        Map<EquipSlot, Item> items = new HashMap<>();

        for(EquipSlot equipSlot : wearables.keySet()){
            Entity e = wearables.get(equipSlot);

            ItemComponent item = im.get(e);
            if(item == null) continue;

            EquipableComponent equipable = em.get(e);

            /* fijate que por ahi el wear type deberia estar dentro de equipable component y no en wear component */
            items.put(equipSlot, new Item(item, new Equipable(equipable)));
        }

        ui.update(items);
    }

    private void onUnequipItemEvent(UnequipItemEvent event){
        Item item = event.getItem();

        WearComponent wearComponent = wm.get(player);
        Entity itemEntity = wearComponent.wearables.remove(item.getEquipable().getEquipSlot());
        BagComponent bagComponent = bm.get(player);
        bagComponent.add(itemEntity);

        refreshEquipperSprite();

        System.out.println(item.getName() + " unequipped");
    }

    private void onEquipItemEvent(EquipItemEvent event) {
        Item item = event.getItem();

        BagComponent bagComponent = bm.get(player);
        Entity itemEntity = bagComponent.remove(item.getInvented().getPositionInBag());
        WearComponent wearComponent = wm.get(player);
        wearComponent.wearables.put(item.getEquipable().getEquipSlot(), itemEntity);

        refreshEquipperSprite();

        System.out.println(item.getName() + " equipped");
    }

    private void refreshEquipperSprite() {
        player.add(new RefreshSpriteRequirementComponent());
    }
}
