package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.facundolinlaud.supergame.builder.ItemBuilder;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.model.equipment.Equipable;
import com.facundolinlaud.supergame.ui.model.inventory.Invented;
import com.facundolinlaud.supergame.ui.view.InventoryUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.Messages;
import com.facundolinlaud.supergame.utils.events.ItemDroppedEvent;
import com.facundolinlaud.supergame.utils.events.ItemsPositionSwapEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facundo on 3/27/16.
 */
public class InventoryUIController implements Telegraph {
    private ComponentMapper<ItemComponent> im = Mappers.item;
    private ComponentMapper<EquipableComponent> em = Mappers.equipable;
    private ComponentMapper<BagComponent> bm = Mappers.bag;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private InventoryUI ui;
    private Entity gatherer;

    public InventoryUIController(InventoryUI ui) {
        this.ui = ui;
    }

    public void setGatherer(Entity gatherer) {
        this.gatherer = gatherer;
    }

    public void update(Entity entity, BagComponent bag) {
        setGatherer(entity);

        List<Item> items = new ArrayList<>();

        for(int i = 0; i < bag.items.size(); i++){
            Entity e = bag.items.get(i);

            ItemComponent itemComponent = im.get(e);
            EquipableComponent equipableComponent = em.get(e);
            Equipable equipable = null;

            if(equipableComponent != null){
                equipable = new Equipable(equipableComponent.getEquipSlot(),
                        equipableComponent.getAttack(), equipableComponent.getDefense());
            }

            items.add(new Item(itemComponent.name, itemComponent.picture, equipable, new Invented(i)));
        }

        ui.updateItems(items);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        switch(msg.message){
            case Messages.ITEM_FROM_INVENTORY_DROPPED:
                itemDropped((ItemDroppedEvent) msg.extraInfo);
                break;
            case Messages.ITEMS_IN_INVENTORY_SWAPPED:
                itemsPositionSwapped((ItemsPositionSwapEvent) msg.extraInfo);
                break;
        }

        return false;
    }

    private void itemDropped(ItemDroppedEvent event){
        PositionComponent gathererPosition = pm.get(gatherer);

        Item itemModel = event.getItem();
        int positionInBag = event.getItem().getInvented().getPositionInBag();

        Entity item = bm.get(gatherer).items.remove(positionInBag);

        new ItemBuilder(item)
                .dropped(gathererPosition.x, gathererPosition.y)
                .withRender(new Sprite(itemModel.getPicture()));

        System.out.println(event.getItem().getName() + " dropped");
    }

    private void itemsPositionSwapped(ItemsPositionSwapEvent event) {
        BagComponent bag = bm.get(gatherer);

        Entity a = bag.items.get(event.getFirstItem().getInvented().getPositionInBag());
        Entity b = bag.items.get(event.getSecondItem().getInvented().getPositionInBag());

        bag.items.set(event.getSecondItem().getInvented().getPositionInBag(), a);
        bag.items.set(event.getFirstItem().getInvented().getPositionInBag(), b);

        System.out.println(event.getFirstItem().getName() + " and " + event.getSecondItem().getName() + " swapped");
    }
}