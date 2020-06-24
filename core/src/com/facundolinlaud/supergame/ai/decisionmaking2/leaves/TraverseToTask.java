package com.facundolinlaud.supergame.ai.decisionmaking2.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.decisionmaking2.BehaviorBlackboard;
import com.facundolinlaud.supergame.ai.pathfinding.LinkedGraphPath;
import com.facundolinlaud.supergame.ai.pathfinding.Node;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderAuthority;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderResult;
import com.facundolinlaud.supergame.behaviortree.PoolableTask;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.ai.TraverseComponent;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Pops: a position-value corresponding to the traverse position
 * Pushes: nothing
 */
public class TraverseToTask extends PoolableTask<BehaviorBlackboard> {
    private static final int MINIMUM_DISTANCE_FROM_PLAYER_DESIRED = 2;
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<TraverseComponent> mtm = Mappers.aiMoveTo;

    private PathFinderAuthority pathFinderAuthority;
    private Entity agent;

    private Vector2 targetPosition;

    @Override
    protected void onBlackboardAvailable(BehaviorBlackboard blackboard) {
        pathFinderAuthority = blackboard.getDomainTaskManager().getPathFinderAuthority();
        agent = getBlackboard().getAgent();
    }

    @Override
    public void activate() {
        targetPosition = stack.pop().getPosition();
        super.activate();
    }

    @Override
    public void tick(float delta) {
        Vector2 agentPosition = pm.get(agent).getPosition();
        PathFinderResult result = pathFinderAuthority.searchNodePath(agentPosition, targetPosition);

        if (!result.isFound()) {
            failed();
            return;
        }

        if (result.getPath().getCount() <= MINIMUM_DISTANCE_FROM_PLAYER_DESIRED) {
            completed();
            return;
        }

        LinkedGraphPath<Node> path = result.getPath();
        updateAgentPath(agent, path);
    }

    private void updateAgentPath(Entity agent, LinkedGraphPath<Node> path) {
        if (mtm.has(agent)) {
            mtm.get(agent).setPath(path);
        } else {
            agent.add(new TraverseComponent(path));
        }
    }
}
