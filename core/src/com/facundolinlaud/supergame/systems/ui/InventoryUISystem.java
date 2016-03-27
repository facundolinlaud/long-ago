package com.facundolinlaud.supergame.systems.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.BagComponent;
import com.facundolinlaud.supergame.components.KeyboardComponent;
import com.facundolinlaud.supergame.helper.Mappers;
import com.facundolinlaud.supergame.service.InventoryUIService;

/**
 * Created by facundo on 3/26/16.
 */
public class InventoryUISystem extends IteratingSystem {
    private ComponentMapper<BagComponent> bm = Mappers.bag;

    private InventoryUIService inventoryUIService;

    public InventoryUISystem(InventoryUIService inventoryUIService) {
        super(Family.all(BagComponent.class, KeyboardComponent.class).get());
        this.inventoryUIService = inventoryUIService;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BagComponent bag = bm.get(entity);
        inventoryUIService.update(entity, bag);
    }
}
