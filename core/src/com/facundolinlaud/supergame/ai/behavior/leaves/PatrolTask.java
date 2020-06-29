package com.facundolinlaud.supergame.ai.behavior.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.behavior.BehaviorBlackboard;
import com.facundolinlaud.supergame.ai.pathfinding.LinkedGraphPath;
import com.facundolinlaud.supergame.ai.pathfinding.Node;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderResult;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.ai.TraverseComponent;
import com.facundolinlaud.supergame.managers.world.PathFindingManager;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.Random;

/**
 * Pops: nothing
 * Pushes: nothing
 */
public class PatrolTask extends Task<BehaviorBlackboard> {
    private static final int MAXIMUM_WALKING_DISTANCE = 4;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private PathFindingManager pathFindingManager;
    private Random random;

    public PatrolTask() {
        this.random = new Random();
    }

    @Override
    protected void onBlackboardAvailable(BehaviorBlackboard blackboard) {
        this.pathFindingManager = getBlackboard().getPathFindingManager();
    }

    @Override
    public void activate() {
        Entity agent = getBlackboard().getAgent();
        PathFinderResult result = calculatePath(agent);
        LinkedGraphPath<Node> path = result.getPath();

        if (!result.isFound() || isAgentsCell(path) || isWalkingDistanceTooLong(path)) {
            failed();
            return;
        }

        Runnable onArrive = () -> completed();
        agent.add(new TraverseComponent(path, onArrive));
    }

    private PathFinderResult calculatePath(Entity agent) {
        Vector2 agentPosition = pm.get(agent).getPosition();
        Vector2 nextObjectiveTile = getNextTargetCell(agentPosition);

        return pathFindingManager.calculate(agentPosition, nextObjectiveTile);
    }

    private Vector2 getNextTargetCell(Vector2 agentPosition) {
        int offsetX = getRandomInteger();
        int offsetY = getRandomInteger();

        return new Vector2(agentPosition.x + offsetX, agentPosition.y + offsetY);
    }

    private int getRandomInteger() {
        return random.nextInt(2 * MAXIMUM_WALKING_DISTANCE) - MAXIMUM_WALKING_DISTANCE;
    }

    private boolean isAgentsCell(LinkedGraphPath<Node> path) {
        return path.isEmpty();
    }

    private boolean isWalkingDistanceTooLong(LinkedGraphPath<Node> path) {
        return path.getCount() > MAXIMUM_WALKING_DISTANCE;
    }
}
