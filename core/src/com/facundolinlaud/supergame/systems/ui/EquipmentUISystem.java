package com.facundolinlaud.supergame.systems.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.ui.controller.EquipmentUIController;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 4/2/16.
 */
public class EquipmentUISystem extends IteratingSystem {
    private ComponentMapper<WearComponent> wm = Mappers.wear;

    private EquipmentUIController equipmentUIController;

    public EquipmentUISystem(EquipmentUIController equipmentUIController) {
        super(Family.all(WearComponent.class, KeyboardComponent.class).get());

        this.equipmentUIController = equipmentUIController;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        WearComponent wearComponent = wm.get(entity);
        equipmentUIController.update(entity, wearComponent);
    }
}
