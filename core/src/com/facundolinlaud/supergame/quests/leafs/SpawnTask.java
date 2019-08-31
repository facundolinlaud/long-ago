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
    private AgentFactory agentFactory;
    private Engine engine;

    public SpawnTask(int agentId, Vector2 position, Blackboard blackboard) {
        this.agentId = agentId;
        this.position = position;
        this.engine = blackboard.getEngine();
        this.agentFactory = blackboard.getAgentFactory();
    }

    @Override
    public void activate() {
        Entity agent = agentFactory.create(agentId)
                .at(position.x, position.y)
                .build();

        engine.addEntity(agent);
        completed();
    }
}
