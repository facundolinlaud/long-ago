package com.facundolinlaud.supergame.ai.decisionmaking;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.utils.Distances;

public class PlayerSeenTask extends LeafTask<Blackboard> {
    private static final float VIEW_DISTANCE = 6f;

    @Override
    public Status execute() {
        Blackboard blackboard = super.getObject();

        if(isPlayerNear(blackboard.getPlayerPosition(), blackboard.getAgentPosition()))
            return Status.SUCCEEDED;
        else
            return Status.RUNNING;
    }

    private boolean isPlayerNear(Vector2 playerPosition, Vector2 agentPosition){
        float distanceToPlayer = Distances.calculateEuclideanDistanceBetween(playerPosition, agentPosition);
        return distanceToPlayer < VIEW_DISTANCE;
    }


    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new PlayerSeenTask();
    }
}
