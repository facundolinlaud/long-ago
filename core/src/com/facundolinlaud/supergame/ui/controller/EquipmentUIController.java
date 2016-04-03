package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.utils.mediator.Receiver;

/**
 * Created by facundo on 4/2/16.
 */
public interface EquipmentUIController extends Receiver {
    void update(Entity entity, WearComponent wear);

    void setEquipper(Entity player);
}
