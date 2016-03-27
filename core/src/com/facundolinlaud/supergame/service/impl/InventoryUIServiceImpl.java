package com.facundolinlaud.supergame.service.impl;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.BagComponent;
import com.facundolinlaud.supergame.components.ItemComponent;
import com.facundolinlaud.supergame.service.InventoryUIService;
import com.facundolinlaud.supergame.ui.InventoryUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.observer.events.InventoryEvent;

/**
 * Created by facundo on 3/27/16.
 */
public class InventoryUIServiceImpl implements InventoryUIService {
    private ComponentMapper<ItemComponent> im = Mappers.item;
    private ComponentMapper<BagComponent> bm = Mappers.bag;

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
        ui.update(bag.items.stream().map(e -> im.get(e).name).toArray());
    }

    @Override
    public void clickedItem(int index) {
        bm.get(gatherer).items.remove(index);
    }

    @Override
    public void handle(InventoryEvent event) {
        bm.get(gatherer).items.remove(event.getSelectedItem());
    }

}
