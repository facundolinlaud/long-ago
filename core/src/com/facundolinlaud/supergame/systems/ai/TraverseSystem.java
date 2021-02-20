package com.facundolinlaud.supergame.systems.ai;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.ai.BehaviorComponent;
import com.facundolinlaud.supergame.components.ai.TraverseComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.PositionUtils;

public class TraverseSystem extends IteratingSystem {
    private static final float EPSILON = 0.2f;

    private ComponentMapper<TraverseComponent> mtm = Mappers.traverse;
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    public TraverseSystem() {
        super(Family.all(BehaviorComponent.class, TraverseComponent.class).get());
    }

    @Override
    protected void processEntity(Entity agent, float deltaTime) {
        TraverseComponent traversal = mtm.get(agent);
        PositionComponent position = pm.get(agent);
        StatusComponent status = sm.get(agent);

        if (traversal.getPathLength() <= 1) {
            onArrive(status, traversal, agent);
        } else {
            Vector2 agentPosition = position.getPosition();
            Vector2 upcomingCell = traversal.getNextCell();

            while(isInside(agentPosition, upcomingCell)){
                traversal.popCell();
                upcomingCell = traversal.getNextCell();
            }

            Direction direction;
            Direction directionToNextCell = PositionUtils.getCellFacingDirection(agentPosition, upcomingCell);

            if (isCenteredForNextCell(agentPosition, upcomingCell)) {
                direction = directionToNextCell;
            } else {
                direction = getFacingToCenterDirection(agentPosition, directionToNextCell);
            }

            status.setAction(Action.WALKING);
            status.setDirection(direction);
        }
    }

    private boolean isInside(Vector2 agentPosition, Vector2 cell) {
        double agentX = Math.floor(agentPosition.x);
        double agentY = Math.floor(agentPosition.y);
        double cellX = Math.floor(cell.x);
        double cellY = Math.floor(cell.y);

        return agentX == cellX && agentY == cellY;
    }

    private Direction getFacingToCenterDirection(Vector2 agentPosition, Direction directionToNextCell) {
        if (directionToNextCell.isVertical()) {
            float fromX = (float) Math.floor(agentPosition.x) + 1 / 2f - EPSILON;

            if (fromX > agentPosition.x) {
                return Direction.RIGHT;
            } else {
                return Direction.LEFT;
            }
        } else {
            float fromY = (float) Math.floor(agentPosition.y) + 1 / 2f - EPSILON;

            if (fromY > agentPosition.y) {
                return Direction.UP;
            } else {
                return Direction.DOWN;
            }
        }
    }

    private boolean isCenteredForNextCell(Vector2 agentPosition, Vector2 nextCell) {
        Direction pathDirection = PositionUtils.getCellFacingDirection(agentPosition, nextCell);

        if (pathDirection.isVertical()) {
            return isCenteredInAxis(agentPosition.x, nextCell.x);
        }

        return isCenteredInAxis(agentPosition.y, nextCell.y);
    }

    private boolean isCenteredInAxis(float agentPosition, float cellPosition) {
        float fromX = cellPosition + 1 / 2f - EPSILON;
        float toX = cellPosition + 1 / 2f + EPSILON;

        return fromX <= agentPosition && agentPosition <= toX;
    }

    private void onArrive(StatusComponent status, TraverseComponent traversal, Entity agent) {
        status.setAction(Action.STANDING);
        agent.remove(TraverseComponent.class);
        traversal.arrive();
    }
}
