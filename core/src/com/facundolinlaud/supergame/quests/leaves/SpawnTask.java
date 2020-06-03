package com.facundolinlaud.supergame.quests.leaves;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.builder.AgentBuilder;
import com.facundolinlaud.supergame.quests.QuestBlackboard;
import com.facundolinlaud.supergame.services.AgentService;

/**
 * Pops: nothing
 * Pushes: the spawned agent
 */
public class SpawnTask extends Task<QuestBlackboard> {
    private String agentId;
    private Vector2 position;

    public SpawnTask(String agentId, Vector2 position) {
        this.agentId = agentId;
        this.position = position;
    }

    @Override
    public void activate() {
        AgentService agentService = getBlackboard().getAgentService();

        AgentBuilder agent = agentService
                .get(agentId)
                .at(position.x, position.y);

        agentService.add(agent);

        stack.push(new Value(agent.build()));
        completed();
    }
}
