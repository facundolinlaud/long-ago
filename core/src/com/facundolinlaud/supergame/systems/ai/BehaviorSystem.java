package com.facundolinlaud.supergame.systems.ai;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.ai.BehaviorComponent;
import com.facundolinlaud.supergame.components.ai.BehavioringComponent;
import com.facundolinlaud.supergame.managers.world.BehaviorManager;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.utils.Distances;
import com.facundolinlaud.supergame.utils.Mappers;

public class BehaviorSystem extends IntervalIteratingSystem {
    private final static float INTERVAL = 0.1f;
    private static final float MINIMUM_DISTANCE_FOR_ACTIVE_IA = 25f;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private BehaviorManager behaviorManager;
    private AgentService agentService;

    public BehaviorSystem(BehaviorManager behaviorManager, AgentService agentService) {
        super(Family.all(BehaviorComponent.class).exclude(BehavioringComponent.class).get(), INTERVAL);
        this.behaviorManager = behaviorManager;
        this.agentService = agentService;
    }

    @Override
    protected void processEntity(Entity agent) {
        Entity player = agentService.getPlayer();

        Vector2 playerPosition = pm.get(player).getPosition();
        Vector2 agentPosition = pm.get(agent).getPosition();

        if (isAgentActive(agentPosition, playerPosition)) {
            behaviorManager.activate(agent);
        }
    }

    private boolean isAgentActive(Vector2 agentPosition, Vector2 playerPosition) {
        return Distances.calculateEuclideanDistanceBetween(playerPosition, agentPosition) < MINIMUM_DISTANCE_FOR_ACTIVE_IA;
    }
}