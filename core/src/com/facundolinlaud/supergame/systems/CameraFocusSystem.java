package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/19/16.
 */
public class CameraFocusSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private CameraManager cameraManager;

    public CameraFocusSystem(CameraManager cameraManager) {
        super(Family.all(PositionComponent.class, KeyboardComponent.class).get());
        this.cameraManager = cameraManager;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        cameraManager.setPosition(position.x, position.y);
    }
}
