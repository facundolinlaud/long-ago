package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.player.WearComponent;

/**
 * Created by facundo on 4/2/16.
 */
public interface EquipmentUIController {
    void update(Entity entity, WearComponent wear);

    void setEquipper(Entity player);
}
