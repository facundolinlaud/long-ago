package com.facundolinlaud.supergame.systems.ai;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.decisionmaking.Blackboard;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.managers.world.AIManager;
import com.facundolinlaud.supergame.utils.Distances;
import com.facundolinlaud.supergame.utils.Mappers;

public class DecisionMakingSystem extends EntitySystem {
    private final static float INTERVAL = 0.5f;
    private static final float MINIMUM_DISTANCE_FOR_ACTIVE_IA = 25f;

    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private Blackboard blackboard;
    private ImmutableArray<Entity> agents;
    private AIManager aiManager;
    private float accumulator = 0f;

    public DecisionMakingSystem(AIManager aiManager) {
        this.blackboard = new Blackboard();
        this.aiManager = aiManager;
    }

    private PositionComponent getPlayerPositionComponent(){
        Entity player = getEngine().getEntitiesFor(Family.all(KeyboardComponent.class).get()).first();
        return pm.get(player);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.agents = engine.getEntitiesFor(Family.all(AIComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        accumulator += deltaTime;

        while(accumulator >= INTERVAL) {
            accumulator -= INTERVAL;
            updateInterval();
        }
    }

    private void updateInterval(){
        Vector2 playerPosition = getPlayerPositionComponent().getPosition();
        blackboard.setPlayerPosition(playerPosition);

        for(Entity agent : agents) {
            Vector2 agentPosition = pm.get(agent).getPosition();

            if(isAgentActive(agentPosition, playerPosition)){
                blackboard.setAgent(agent);
                blackboard.setAgentPosition(agentPosition);
                aiManager.step(agent, blackboard);
            }
        }
    }

    private boolean isAgentActive(Vector2 agentPosition, Vector2 playerPosition) {
        return Distances.calculateEuclideanDistanceBetween(playerPosition, agentPosition) < MINIMUM_DISTANCE_FOR_ACTIVE_IA;
    }
}