package com.facundolinlaud.supergame.ai;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.utils.Mappers;

public class FaceTowardsPlayerTask extends LeafTask<Blackboard> {
    private ComponentMapper<StatusComponent> sm = Mappers.status;


    public FaceTowardsPlayerTask() {}

    @Override
    public Status execute() {
        Blackboard blackboard = getObject();

        Vector2 agentPosition = blackboard.getAgentPosition();
        Vector2 playerPosition = blackboard.getPlayerPosition();

        Direction newAgentDirection = calculateAgentDirection(agentPosition, playerPosition);
        Entity agent = blackboard.getAgent();
        StatusComponent agentStatus = sm.get(agent);
        agentStatus.setDirection(newAgentDirection);

        return Status.SUCCEEDED;
    }

    private Direction calculateAgentDirection(Vector2 agentPosition, Vector2 playerPosition) {
        float offsetX = playerPosition.x - agentPosition.x;
        float offsetY = playerPosition.y - agentPosition.y;

        if(Math.abs(offsetX) > Math.abs(offsetY)){
           if(offsetX > 0){
               return Direction.RIGHT;
           }else{
               return Direction.LEFT;
           }
        }else{
            if(offsetY > 0){
                return Direction.UP;
            }else{
                return Direction.DOWN;
            }
        }
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new FaceTowardsPlayerTask();
    }
}
