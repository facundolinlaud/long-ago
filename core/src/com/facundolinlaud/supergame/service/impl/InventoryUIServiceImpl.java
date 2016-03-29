package com.facundolinlaud.supergame.service.impl;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.BagComponent;
import com.facundolinlaud.supergame.components.ItemComponent;
import com.facundolinlaud.supergame.components.PickupableComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.service.InventoryUIService;
import com.facundolinlaud.supergame.ui.InventoryUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.mediator.events.ItemDroppedEvent;

/**
 * Created by facundo on 3/27/16.
 */
public class InventoryUIServiceImpl implements InventoryUIService {
    private ComponentMapper<ItemComponent> itc = Mappers.item;
    private ComponentMapper<BagComponent> bm = Mappers.bag;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private InventoryUI ui;
    private Entity gatherer;

    public InventoryUIServiceImpl(InventoryUI ui) {
        this.ui = ui;
    }

    @Override
    public void setGatherer(Entity gatherer) {
        this.gatherer = gatherer;
    }

    @Override
    public void update(Entity entity, BagComponent bag) {
        setGatherer(entity);
        ui.updateItems(bag.items.stream().map(e -> itc.get(e).name).toArray());
    }

    @Override
    public void handle(ItemDroppedEvent event) {
        PositionComponent gathererPosition = pm.get(gatherer);

        Entity item = bm.get(gatherer).items.remove(event.getItemIndex());

        item.add(new PositionComponent(gathererPosition));
        item.add(new PickupableComponent());
    }
}
