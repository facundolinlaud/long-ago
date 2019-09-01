package com.facundolinlaud.supergame.ai.decisionmaking;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.PositionUtils;

public class FaceTowardsPlayerTask extends LeafTask<Blackboard> {
    private ComponentMapper<StatusComponent> sm = Mappers.status;


    public FaceTowardsPlayerTask() {}

    @Override
    public Status execute() {
        Blackboard blackboard = getObject();

        Vector2 agentPosition = blackboard.getAgentPosition();
        Vector2 playerPosition = blackboard.getPlayerPosition();

        Direction newAgentDirection = PositionUtils.getFacingDirection(agentPosition, playerPosition);
        Entity agent = blackboard.getAgent();
        StatusComponent agentStatus = sm.get(agent);
        agentStatus.setDirection(newAgentDirection);

        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new FaceTowardsPlayerTask();
    }
}
