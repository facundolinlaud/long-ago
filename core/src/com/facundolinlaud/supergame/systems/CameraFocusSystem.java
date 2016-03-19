package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.facundolinlaud.supergame.components.InputComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.helper.Mappers;

/**
 * Created by facundo on 3/19/16.
 */
public class CameraFocusSystem extends IteratingSystem {
    public static final int Z = 0;

    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private Camera camera;

    public CameraFocusSystem(Camera camera) {
        super(Family.all(PositionComponent.class, InputComponent.class).get());
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);

        camera.position.set(position.x, position.y, Z);
        camera.update();
    }
}
