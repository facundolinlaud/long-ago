package com.facundolinlaud.supergame.systems.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.AttributesComponent;
import com.facundolinlaud.supergame.components.KeyboardComponent;
import com.facundolinlaud.supergame.ui.controller.AttributesUIController;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/30/16.
 */
public class AttributesUISystem extends IteratingSystem {
    private ComponentMapper<AttributesComponent> am = Mappers.attributes;

    private AttributesUIController attributesUIController;

    public AttributesUISystem(AttributesUIController attributesUIController) {
        super(Family.all(AttributesComponent.class, KeyboardComponent.class).get());

        this.attributesUIController = attributesUIController;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AttributesComponent attributesComponent = am.get(entity);
        attributesUIController.update(entity, attributesComponent);
    }
}
