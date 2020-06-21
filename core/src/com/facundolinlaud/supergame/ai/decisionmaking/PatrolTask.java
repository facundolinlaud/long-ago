package com.facundolinlaud.supergame.ai.decisionmaking;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.pathfinding.LinkedGraphPath;
import com.facundolinlaud.supergame.ai.pathfinding.Node;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderAuthority;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderResult;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.ai.TraverseComponent;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.Random;

public class PatrolTask extends LeafTask<Blackboard> {
    private static final int MAXIMUM_WALKING_DISTANCE = 4;

    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private PathFinderAuthority pathFinderAuthority;
    private Random random;

    public PatrolTask(PathFinderAuthority pathFinderAuthority) {
        this.pathFinderAuthority = pathFinderAuthority;
        this.random = new Random();
    }

    @Override
    public Status execute() {
        Blackboard blackboard = getObject();
        Entity agent = blackboard.getAgent();
        StatusComponent agentStatus = sm.get(agent);

        if(status.equals(Status.RUNNING)){
            switch(agentStatus.getAction()){
                case STANDING:
                    return Status.SUCCEEDED;
                default:
                    return Status.RUNNING;
            }
        }

        PathFinderResult result = getPathFinderResult(blackboard);
        LinkedGraphPath<Node> path = result.getPath();

        if(!result.isFound() || isAgentsCell(path) || cellIsBehindTooManyObstacles(path))
            return Status.FAILED;

        agent.add(new TraverseComponent(path));

        return Status.RUNNING;
    }

    private Vector2 getNextTargetCell(Vector2 agentPosition){
        int offsetX = getRandomInteger();
        int offsetY = getRandomInteger();

        return new Vector2(agentPosition.x + offsetX, agentPosition.y + offsetY);
    }

    private int getRandomInteger(){
        return random.nextInt(2 * MAXIMUM_WALKING_DISTANCE) - MAXIMUM_WALKING_DISTANCE;
    }

    private boolean isAgentsCell(LinkedGraphPath<Node> path) {
        return path.isEmpty();
    }

    private boolean cellIsBehindTooManyObstacles(LinkedGraphPath<Node> path) {
        return path.getCount() > MAXIMUM_WALKING_DISTANCE;
    }

    private PathFinderResult getPathFinderResult(Blackboard blackboard) {
        Vector2 agentPosition = blackboard.getAgentPosition();
        Vector2 nextObjectiveTile = getNextTargetCell(agentPosition);
        return pathFinderAuthority.searchNodePath(agentPosition, nextObjectiveTile);
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new PatrolTask(this.pathFinderAuthority);
    }
}
