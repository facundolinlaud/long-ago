package com.facundolinlaud.supergame.systems.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.BagComponent;
import com.facundolinlaud.supergame.components.KeyboardComponent;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.ui.controller.InventoryUIController;

/**
 * Created by facundo on 3/26/16.
 */
public class InventoryUISystem extends IteratingSystem {
    private ComponentMapper<BagComponent> bm = Mappers.bag;

    private InventoryUIController inventoryUIController;

    public InventoryUISystem(InventoryUIController inventoryUIController) {
        super(Family.all(BagComponent.class, KeyboardComponent.class).get());
        this.inventoryUIController = inventoryUIController;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BagComponent bag = bm.get(entity);
        inventoryUIController.update(entity, bag);
    }
}
