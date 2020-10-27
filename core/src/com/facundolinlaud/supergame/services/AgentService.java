package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.facundolinlaud.supergame.builder.AgentBuilder;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.factory.AgentFactory;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.shape.Shape;

import java.util.LinkedList;
import java.util.List;

public class AgentService extends Service {
    public static final String PLAYER_ID = "player";
    public static final String PLAYER_TAG = "player";
    private ComponentMapper<KeyboardComponent> km = Mappers.keyboard;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private AgentFactory agentFactory;
    private Entity player;

    public AgentService(Engine engine, AgentFactory agentFactory) {
        super(engine);
        this.agentFactory = agentFactory;

        AgentBuilder player = agentFactory.create(PLAYER_ID).withKeyboardControl().at(0, 0);
        this.add(player, PLAYER_TAG);
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

    public AgentBuilder get(String id) {
        return agentFactory.create(id);
    }

    public void add(AgentBuilder agentBuilder, String agentTag) {
        agentBuilder.withTag(agentTag);

        Entity agent = agentBuilder.build();

        if (isKeyboardControlled(agent)) {
            player = agent;
        }

        getEngine().addEntity(agent);
    }

    private boolean isKeyboardControlled(Entity entity) {
        return km.has(entity);
    }

    public boolean isPlayer(Entity agent) {
        return agent == player;
    }

    public Entity getPlayer() {
        return player;
    }
}
