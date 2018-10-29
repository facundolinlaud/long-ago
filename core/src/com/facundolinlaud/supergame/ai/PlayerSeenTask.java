package com.facundolinlaud.supergame.ai;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.utils.Distances;
import com.facundolinlaud.supergame.utils.Mappers;

public class PlayerSeenTask extends LeafTask<Blackboard> {
    private static final float VIEW_DISTANCE = 5f;

    private ComponentMapper<PositionComponent> pm = Mappers.position;

    @Override
    public Status execute() {
        Blackboard blackboard = super.getObject();

        if(isPlayerNear(blackboard.getPlayerPosition(), blackboard.getAgentPosition())){
            System.out.println("soy agent " + blackboard.getAgent() + " y PLAYER SEEN SUCCEED");
            return Status.SUCCEEDED;
        } else {
            System.out.println("soy agent " + blackboard.getAgent() + " y player seen fail");
            return Status.RUNNING;
        }
    }

    private boolean isPlayerNear(Vector2 playerPosition, Vector2 agentPosition){
        float distanceToPlayer = Distances.calculateEuclideanDistanceBetween(playerPosition, agentPosition);
        System.out.println("distance to player: " + distanceToPlayer);
        return distanceToPlayer < VIEW_DISTANCE;
    }


    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return null;
    }
}
