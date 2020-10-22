package com.facundolinlaud.supergame.quests.leaves;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.builder.AgentBuilder;
import com.facundolinlaud.supergame.quests.QuestBlackboard;
import com.facundolinlaud.supergame.services.AgentService;

/**
 * Pops: nothing
 * Pushes: the spawned agent
 */
public class SpawnTask extends LeafTask<QuestBlackboard> {
    private String agentId;
    private String agentTag;
    private Vector2 position;

    public SpawnTask(String agentId, String agentTag, Vector2 position) {
        this.agentId = agentId;
        this.agentTag = agentTag;
        this.position = position;
    }

    @Override
    public void activate() {
        AgentService agentService = getBlackboard().getAgentService();

        AgentBuilder agent = agentService
                .get(agentId)
                .at(position.x, position.y);

        agentService.add(agent, agentTag);

        stack.push(new Value(agent.build()));
        completed();
    }
}
