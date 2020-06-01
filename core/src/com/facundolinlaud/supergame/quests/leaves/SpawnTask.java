package com.facundolinlaud.supergame.quests.leaves;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.factory.AgentFactory;
import com.facundolinlaud.supergame.quests.QuestBlackboard;

public class SpawnTask extends Task<QuestBlackboard> {
    private String agentId;
    private Vector2 position;

    public SpawnTask(String agentId, Vector2 position) {
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
