package com.facundolinlaud.supergame.listeners;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.helper.Mappers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 3/26/16.
 */
public class PhysicsEntitiesListener implements EntityListener {
    private ComponentMapper<BodyComponent> bm = Mappers.body;

    private World world;
    private Map<Entity, Body> entitiesToBodies;

    public PhysicsEntitiesListener(World world) {
        this.entitiesToBodies = new HashMap<Entity, Body>();
        this.world = world;
    }

    @Override
    public void entityAdded(Entity entity) {
        BodyComponent bodyComponent = bm.get(entity);
        entitiesToBodies.put(entity, bodyComponent.body);
    }

    @Override
    public void entityRemoved(Entity entity) {
        world.destroyBody(entitiesToBodies.get(entity));
    }
}
