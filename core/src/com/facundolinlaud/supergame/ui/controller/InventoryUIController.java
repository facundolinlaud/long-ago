package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.utils.mediator.Receiver;

/**
 * Created by facundo on 3/27/16.
 */
public interface InventoryUIController extends Receiver {

    void update(Entity entity, BagComponent bag);

    void setGatherer(Entity player);
}
