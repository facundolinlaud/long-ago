package com.facundolinlaud.supergame.ui.services;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.BagComponent;
import com.facundolinlaud.supergame.utils.mediator.Receiver;
import com.facundolinlaud.supergame.utils.events.ItemDroppedEvent;

/**
 * Created by facundo on 3/27/16.
 */
public interface InventoryUIService extends Receiver {

    void update(Entity entity, BagComponent bag);

    void setGatherer(Entity player);
}
