package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.shape.Shape;

import java.util.LinkedList;
import java.util.List;

public class AgentsService extends Service {
    private ComponentMapper<KeyboardComponent> km = Mappers.keyboard;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    public AgentsService(Engine engine) {
        super(engine);
    }

    public List<Entity> in(Shape area) {
        List<Entity> entities = new LinkedList();
        ImmutableArray<Entity> allEntities = getEngine().getEntitiesFor(Family.all(
                HealthComponent.class,
                PositionComponent.class).get());

        for (Entity e : allEntities) {
            PositionComponent entityPosition = pm.get(e);

            if (area.contains(entityPosition.x, entityPosition.y))
                entities.add(e);
        }

        return entities;
    }

    public boolean isMainPlayer(Entity entity) {
        return km.has(entity);
    }
}
