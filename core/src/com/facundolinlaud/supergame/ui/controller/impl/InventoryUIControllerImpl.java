package com.facundolinlaud.supergame.ui.controller.impl;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.factory.PhysicsFactory;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.items.PickupableComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.factory.TextureFactory;
import com.facundolinlaud.supergame.model.RenderPriority;
import com.facundolinlaud.supergame.ui.controller.InventoryUIController;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.model.equipment.Equipable;
import com.facundolinlaud.supergame.ui.model.inventory.Invented;
import com.facundolinlaud.supergame.ui.view.InventoryUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.Event;
import com.facundolinlaud.supergame.utils.events.ItemDroppedEvent;
import com.facundolinlaud.supergame.utils.events.ItemsPositionSwapEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facundo on 3/27/16.
 */
public class InventoryUIControllerImpl implements InventoryUIController {
    private ComponentMapper<ItemComponent> im = Mappers.item;
    private ComponentMapper<EquipableComponent> em = Mappers.equipable;
    private ComponentMapper<BagComponent> bm = Mappers.bag;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private InventoryUI ui;
    private Entity gatherer;

    public InventoryUIControllerImpl(InventoryUI ui) {
        this.ui = ui;
    }

    @Override
    public void setGatherer(Entity gatherer) {
        this.gatherer = gatherer;
    }

    @Override
    public void update(Entity entity, BagComponent bag) {
        setGatherer(entity);

        List<Item> items = new ArrayList<>();

        for(int i = 0; i < bag.items.size(); i++){
            Entity e = bag.items.get(i);

            ItemComponent itemComponent = im.get(e);
            EquipableComponent equipableComponent = em.get(e);
            Equipable equipable = null;

            if(equipableComponent != null){
                equipable = new Equipable(equipableComponent.wearType, equipableComponent.attack, equipableComponent.defense);
            }

            items.add(new Item(itemComponent.name, itemComponent.picture, equipable, new Invented(i)));
        }

        ui.updateItems(items);
    }

    @Override
    public void handle(Event event) {
        if(event instanceof ItemDroppedEvent){
            itemDropped((ItemDroppedEvent) event);
        }else if(event instanceof ItemsPositionSwapEvent){
            itemsPositionSwapped((ItemsPositionSwapEvent) event);
        }
    }

    private void itemDropped(ItemDroppedEvent event){
        PositionComponent gathererPosition = pm.get(gatherer);

        int positionInBag = event.getItem().getInvented().getPositionInBag();
        Entity item = bm.get(gatherer).items.remove(positionInBag);

        item.add(new PositionComponent(gathererPosition));
        item.add(new RenderComponent(new TextureRegion(event.getItem().getPicture())));
//        item.add(new RenderComponent(new TextureRegion(TextureFactory.getTexture("pictures/items/weapon/spear.png"))));
        item.add(new PickupableComponent());
//        item.add(new ItemComponent(TextureFactory.getTexture(texturePath)));
        item.add(new BodyComponent(PhysicsFactory.get().createItemBody()));

//        BodyComponent b = Mappers.body.get(item);
//        b.body.setTransform(5f, 5f, b.body.getAngle());

        System.out.println("---------------------------");
        System.out.println("Item " + event.getItem().getName() + " dropped");

//        PositionComponent newItemPosition = pm.get(item);
//        System.out.println("item dropeado estaba en:         " + newItemPosition.x + ", " + newItemPosition.y);
        System.out.println("tiene los componentes: " + item.getComponents());
        System.out.println("---------------------------");
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