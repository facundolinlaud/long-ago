package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.AttributesComponent;
import com.facundolinlaud.supergame.utils.mediator.Receiver;

/**
 * Created by facundo on 3/30/16.
 */
public interface AttributesUIController extends Receiver {
    void update(Entity player, AttributesComponent attributesComponent);
}
