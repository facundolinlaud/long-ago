package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.components.AttributesComponent;
import com.facundolinlaud.supergame.ui.model.Attributes;
import com.facundolinlaud.supergame.ui.view.AttributesUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.Messages;
import com.facundolinlaud.supergame.utils.events.AttributeUpgradeEvent;

/**
 * Created by facundo on 3/30/16.
 */
public class AttributesUIController implements Telegraph {
    private static final int ATTRIBUTE_ADDING_STEP = 1;
    private ComponentMapper<AttributesComponent> am = Mappers.attributes;

    private AttributesUI attributesUI;
    private Entity player;

    public AttributesUIController(AttributesUI attributesUI) {
        this.attributesUI = attributesUI;
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        switch(msg.message){
            case Messages.ATTRIBUTE_UPGRADED:
                handleAttributeUpgradedEvent((AttributeUpgradeEvent) msg.extraInfo);
                break;
        }
        return false;
    }

    public void update(Entity player, AttributesComponent attrsComponent) {
        this.player = player;
        Attributes attributes = new Attributes(attrsComponent.getAgility(),
                attrsComponent.getStrength(), attrsComponent.getIntelligence(), attrsComponent.getStamina());

        attributesUI.update(attributes);
    }

    private void handleAttributeUpgradedEvent(AttributeUpgradeEvent event){
        String attribute = event.getAttribute();
        AttributesComponent attributesComponent = am.get(player);
        attributesComponent.add(attribute, ATTRIBUTE_ADDING_STEP);
    }
}
