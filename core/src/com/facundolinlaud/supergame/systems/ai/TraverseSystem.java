package com.facundolinlaud.supergame.systems.ai;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.components.ai.TraverseComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.utils.Mappers;
import java.awt.Point;

public class TraverseSystem extends IteratingSystem {
    private static final float EPSILON = 0.5f;

    private ComponentMapper<TraverseComponent> mtm = Mappers.aiMoveTo;
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    public TraverseSystem() {
        super(Family.all(AIComponent.class, TraverseComponent.class).get());
    }

    @Override
    protected void processEntity(Entity agent, float deltaTime) {
        decideWhetherToKeepWalkingOrNot(agent);
    }

    private void decideWhetherToKeepWalkingOrNot(Entity agent) {
        TraverseComponent traversal = mtm.get(agent);
        PositionComponent position = pm.get(agent);
        StatusComponent status = sm.get(agent);

        Point targetCell = traversal.getNextCell();
        Vector2 agentPosition = position.getPosition();

        float differenceX = agentPosition.x - targetCell.x;
        float differenceY = agentPosition.y - targetCell.y;

        float deltaX = Math.abs(differenceX);
        float deltaY = Math.abs(differenceY);

        if(deltaX > EPSILON || deltaY > EPSILON){
            Direction newDirection = resolveDirection(differenceX, differenceY, deltaX, deltaY);
            status.setDirection(newDirection);
            status.setAction(Action.WALKING);
        }else if(traversal.getPathLength() > 1) {
            traversal.popCell();
        }else{
            status.setAction(Action.STANDING);
            agent.remove(TraverseComponent.class);
            traversal.arrive();
        }
    }

    private Direction resolveDirection(float differenceX, float differenceY, float deltaX, float deltaY) {
        if(deltaX > EPSILON){
            if(differenceX > 0){
                return Direction.LEFT;
            }else{
                return Direction.RIGHT;
            }
        }else{
            if(differenceY > 0){
                return Direction.DOWN;
            }else{
                return Direction.UP;
            }
        }
    }
}
