package com.facundolinlaud.supergame.quests.leafs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.factory.AgentFactory;
import com.facundolinlaud.supergame.quests.Blackboard;
import com.facundolinlaud.supergame.quests.Task;

public class SpawnTask extends Task {
    private int agentId;
    private Vector2 position;

    public SpawnTask(int agentId, Vector2 position) {
        this.agentId = agentId;
        this.position = position;
    }

    @Override
    public void activate() {
        Engine engine = getBlackboard().getEngine();
        AgentFactory agentFactory = getBlackboard().getAgentFactory();

        Entity agent = agentFactory.create(agentId)
                .at(position.x, position.y)
                .build();

        engine.addEntity(agent);
        completed();
    }
}
