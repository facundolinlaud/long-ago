package com.facundolinlaud.supergame.ui.controller.impl;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.AttributesComponent;
import com.facundolinlaud.supergame.ui.controller.AttributesUIController;
import com.facundolinlaud.supergame.ui.model.Attributes;
import com.facundolinlaud.supergame.ui.view.AttributesUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.AttributeUpgradeEvent;
import com.facundolinlaud.supergame.utils.events.Event;

/**
 * Created by facundo on 3/30/16.
 */
public class AttributesUIControllerImpl implements AttributesUIController {
    public static final int ATTRIBUTE_ADDING_STEP = 1;
    private ComponentMapper<AttributesComponent> am = Mappers.attributes;

    private AttributesUI attributesUI;
    private Entity player;

    public AttributesUIControllerImpl(AttributesUI attributesUI) {
        this.attributesUI = attributesUI;
    }

    @Override
    public void update(Entity player, AttributesComponent attrsComponent) {
        this.player = player;
        Attributes attributes = new Attributes(attrsComponent.agility, attrsComponent.strength, attrsComponent.intelligence, attrsComponent.stamina);
        attributesUI.update(attributes);
    }

    @Override
    public void handle(Event event) {
        String attribute = ((AttributeUpgradeEvent) event).getAttribute();
        AttributesComponent attributesComponent = am.get(player);
        attributesComponent.add(attribute, ATTRIBUTE_ADDING_STEP);
    }
}
