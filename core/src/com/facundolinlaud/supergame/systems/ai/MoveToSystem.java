package com.facundolinlaud.supergame.systems.ai;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.components.ai.AIMoveToComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.utils.Mappers;
import java.awt.Point;

public class MoveToSystem extends IteratingSystem {
    private static final float EPSILON = 0.5f;

    private ComponentMapper<AIMoveToComponent> mtm = Mappers.aiMoveTo;
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    public MoveToSystem() {
        super(Family.all(AIComponent.class, AIMoveToComponent.class).get());
    }

    @Override
    protected void processEntity(Entity agent, float deltaTime) {
        decideWhetherToKeepWalkingOrNot(agent);
    }

    private void decideWhetherToKeepWalkingOrNot(Entity agent) {
        AIMoveToComponent moveTo = mtm.get(agent);
        PositionComponent position = pm.get(agent);
        StatusComponent status = sm.get(agent);

        Point targetCell = moveTo.getNextCell();
        Vector2 agentPosition = position.getPosition();

        float differenceX = agentPosition.x - targetCell.x;
        float differenceY = agentPosition.y - targetCell.y;

        float deltaX = Math.abs(differenceX);
        float deltaY = Math.abs(differenceY);

        if(deltaX > EPSILON || deltaY > EPSILON){
            Direction newDirection = resolveDirection(differenceX, differenceY, deltaX, deltaY);
            status.setDirection(newDirection);
            status.setAction(Action.WALKING);
        }else if(moveTo.getPathLength() > 1) {
            moveTo.popCell();
        }else{
            status.setAction(Action.STANDING);
            agent.remove(AIMoveToComponent.class);
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
