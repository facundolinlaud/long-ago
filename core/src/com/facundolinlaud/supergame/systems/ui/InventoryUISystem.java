package com.facundolinlaud.supergame.systems.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.GathererComponent;
import com.facundolinlaud.supergame.components.KeyboardComponent;
import com.facundolinlaud.supergame.helper.Mappers;
import com.facundolinlaud.supergame.ui.InventoryUI;

/**
 * Created by facundo on 3/26/16.
 */
public class InventoryUISystem extends IteratingSystem {
    private ComponentMapper<GathererComponent> gm = Mappers.gatherer;

    private InventoryUI inventoryUI;

    public InventoryUISystem(InventoryUI inventoryUI) {
        super(Family.all(GathererComponent.class, KeyboardComponent.class).get());
        this.inventoryUI = inventoryUI;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        GathererComponent gatherer = gm.get(entity);
        inventoryUI.update(gatherer.items);
    }
}
