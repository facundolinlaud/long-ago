package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.services.InventoryService;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.model.equipment.Equipable;
import com.facundolinlaud.supergame.ui.model.inventory.Invented;
import com.facundolinlaud.supergame.ui.view.InventoryUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.ItemDroppedEvent;
import com.facundolinlaud.supergame.utils.events.ItemsPositionSwapEvent;
import com.facundolinlaud.supergame.utils.events.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facundo on 3/27/16.
 */
public class InventoryUIController implements Telegraph {
    private ComponentMapper<ItemComponent> im = Mappers.item;
    private ComponentMapper<EquipableComponent> em = Mappers.equipable;

    private InventoryUI ui;
    private InventoryService inventoryService;

    public InventoryUIController(InventoryUI ui, InventoryService inventoryService) {
        this.ui = ui;
        this.inventoryService = inventoryService;

        updateInventory();
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        switch (msg.message) {
            case Messages.ITEM_FROM_INVENTORY_DROPPED:
                onItemDropped((ItemDroppedEvent) msg.extraInfo);
                break;
            case Messages.ITEMS_IN_INVENTORY_SWAPPED:
                onItemsPositionSwapped((ItemsPositionSwapEvent) msg.extraInfo);
                break;
            case Messages.INVENTORY_CHANGED:
                updateInventory();
                break;
        }

        return false;
    }

    public void updateInventory() {
        BagComponent bag = inventoryService.getPlayerBag();

        List<Item> items = new ArrayList<>();
        int gold = bag.getGold();

        for (int i = 0; i < bag.size(); i++) {
            Entity e = bag.get(i);

            ItemComponent itemComponent = im.get(e);
            EquipableComponent equipableComponent = em.get(e);
            Equipable equipable = null;

            if (equipableComponent != null)
                equipable = new Equipable(equipableComponent);

            items.add(new Item(itemComponent, equipable, new Invented(i)));
        }

        ui.updateItems(items, gold);
    }

    private void onItemDropped(ItemDroppedEvent event) {
        int positionInBag = event.getItem().getInvented().getPositionInBag();
        inventoryService.drop(positionInBag);

        System.out.println(event.getItem().getName() + " dropped");
    }

    private void onItemsPositionSwapped(ItemsPositionSwapEvent e) {
        int fromBagPositio = e.getFirstItem().getInvented().getPositionInBag();
        int toBagPosition = e.getSecondItem().getInvented().getPositionInBag();
        inventoryService.swap(fromBagPositio, toBagPosition);

        System.out.println(e.getFirstItem().getName() + " and " + e.getSecondItem().getName() + " swapped");
    }
}