package com.facundolinlaud.supergame.ui.controller.impl;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.ui.view.LabelDamagesUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.Messages;
import com.facundolinlaud.supergame.utils.Position;
import com.facundolinlaud.supergame.utils.events.EntityAttackedEvent;


public class LabelDamagesController implements Telegraph {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private LabelDamagesUI labelDamagesUI;
    private Camera camera;

    public LabelDamagesController(LabelDamagesUI labelDamagesUI, Camera camera) {
        this.labelDamagesUI = labelDamagesUI;
        this.camera = camera;
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        if(msg.message == Messages.ENTITY_ATTACKED)
            registerNewDamageLabel((EntityAttackedEvent) msg.extraInfo);

        return true;
    }

    private void registerNewDamageLabel(EntityAttackedEvent event) {
        PositionComponent positionComponent = pm.get(event.getVictim());
        Position worldPosition = positionComponent.getWorldPosition();

        Position screenPosition = getScreenPosition(worldPosition);
        int damage = (int) event.getDamage();

        this.labelDamagesUI.registerNewDamageLabel(screenPosition, damage);
    }

    private Position getScreenPosition(Position worldPosition){
        Vector3 labelWorldPosition = new Vector3(worldPosition.x, worldPosition.y, 0f);
        Vector3 unprojection = camera.project(labelWorldPosition);

        return new Position(unprojection.x, unprojection.y);
    }
}
