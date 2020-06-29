package com.facundolinlaud.supergame.ai.behavior.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.components.AgentComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.shape.Circle;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Pops: nothing
 * Pushes: a near enemy if seen or fails if none
 */
public class EnemySeenTask extends LeafTask {
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<AgentComponent> am = Mappers.agent;

    private AgentService agentService;

    private float viewDistance;
    private List<String> enemyFactions;
    private Vector2 position;

    public EnemySeenTask(float viewDistance, List<String> enemyFactions) {
        this.viewDistance = viewDistance;
        this.enemyFactions = enemyFactions;
    }

    @Override
    protected void onBlackboardAvailable(Blackboard blackboard) {
        agentService = blackboard.getAgentService();
    }

    @Override
    public void activate() {
        Entity agent = getBlackboard().getAgent();
        position = pm.get(agent).getPosition();
        Circle circle = new Circle(viewDistance, position);

        List<Entity> nearAgents = agentService.in(circle);

        if (nearAgents.contains(agent)) {
            nearAgents.remove(agent);
        }

        nearAgents = nearAgents
                .stream()
                .filter(this::filterEnemyFaction)
                .sorted(this::sortByCloseness)
                .collect(Collectors.toList());

        if (nearAgents.isEmpty()) {
            System.out.println("[EnemySeen] nobody");
            failed();
        } else {
            Entity nearAgent = nearAgents.get(0);
            System.out.println("[EnemySeen] seen " + nearAgent);
            Value value = new Value(nearAgent);
            stack.push(value);
            completed();
        }

        return;
    }

    private int sortByCloseness(Entity agent1, Entity agent2) {
        Vector2 d1 = pm.get(agent1).getPosition();
        Vector2 d2 = pm.get(agent2).getPosition();

        float diff = d1.dst(position) - d2.dst(position);

        if (diff < 0) {
            return -1;
        } else if (diff > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private boolean filterEnemyFaction(Entity agent) {
        AgentComponent agentComponent = am.get(agent);
        String faction = agentComponent.getFactionId();

        return enemyFactions.contains(faction);
    }
}
