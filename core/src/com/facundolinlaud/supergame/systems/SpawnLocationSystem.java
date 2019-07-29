package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.spawn.SpawnLocationComponent;
import com.facundolinlaud.supergame.components.spawn.SpawnedAgentComponent;
import com.facundolinlaud.supergame.factory.AgentFactory;
import com.facundolinlaud.supergame.utils.Dimensions;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.Messages;
import com.facundolinlaud.supergame.utils.events.AgentDiedEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpawnLocationSystem extends IntervalIteratingSystem implements Telegraph {
    public static final int ZERO_AGENTS_SPAWNED = 0;
    private static float INTERVAL = 8f;

    private ComponentMapper<SpawnLocationComponent> sl = Mappers.spawnLocation;
    private ComponentMapper<SpawnedAgentComponent> sa = Mappers.spawnedAgent;

    private Map<Entity, Integer> spawns;
    private AgentFactory agentFactory;
    private Random random;


    public SpawnLocationSystem(AgentFactory agentFactory) {
        super(Family.all(SpawnLocationComponent.class).get(), INTERVAL);

        this.agentFactory = agentFactory;
        this.spawns = new HashMap();
        this.random = new Random();

        MessageManager.getInstance().addListener(this, Messages.AGENT_DIED);
    }

    @Override
    protected void processEntity(Entity spawnEntity) {
        SpawnLocationComponent spawnLocation = sl.get(spawnEntity);

        if(!spawns.containsKey(spawnEntity))
            spawns.put(spawnEntity, ZERO_AGENTS_SPAWNED);

        if(shouldSpawnAgent(spawnEntity, spawnLocation)){
            Vector2 spawnPosition = getPointInside(spawnLocation);

            spawns.put(spawnEntity, spawns.get(spawnEntity) + 1);

            Entity agent = agentFactory.getAI(spawnLocation.getAgentID())
                    .at(spawnPosition.x, spawnPosition.y)
                    .build();

            agent.add(new SpawnedAgentComponent(spawnEntity));

            getEngine().addEntity(agent);
        }
    }

    private boolean shouldSpawnAgent(Entity spawnEntity, SpawnLocationComponent spawnLocation){
        int currentNumberOfAgents = spawns.get(spawnEntity);
        return currentNumberOfAgents < spawnLocation.getAgentsCount();
    }

    private Vector2 getPointInside(SpawnLocationComponent spawnLocation) {
        Rectangle r = spawnLocation.getRectangle();

        float x = Dimensions.toMeters(getRandomNumberBetween(r.x, r.x + r.width));
        float y = Dimensions.toMeters(getRandomNumberBetween(r.y, r.y + r.height));

        return new Vector2(x, y);
    }

    private float getRandomNumberBetween(float a, float b){
        return a + random.nextFloat() * (b - a);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        AgentDiedEvent event = (AgentDiedEvent) msg.extraInfo;
        removeAgentFromSpawnListsIfExists(event.getAgent());

        return true;
    }

    private void removeAgentFromSpawnListsIfExists(Entity agent) {
        if(sa.has(agent)){
            SpawnedAgentComponent spawnedAgent = sa.get(agent);
            Entity spawnEntity = spawnedAgent.getSpawnLocation();
            spawns.put(spawnEntity, spawns.get(spawnEntity) - 1);
        }
    }
}
