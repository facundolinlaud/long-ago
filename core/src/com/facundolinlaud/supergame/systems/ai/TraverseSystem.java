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

/*
 * Refactor this some day. This assumes the path in TraverseComponent always starts at the agents position
*/
public class TraverseSystem extends IteratingSystem {
    private static final float METERS_PER_NODE = 1f;
    private static final float EPSILON = 0.1f;
    private static final float DELTA = 1 / 2f - EPSILON;
    private static final float HALF_METER = 0.5f;
    private static final float ONE_METER = 1f;

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

        Vector2 agentPosition = position.getPosition();

        float seekedProximity = Math.max(traversal.getSeekedProximity(), ONE_METER);
        boolean closeEnough = traversal.getPathLength() * METERS_PER_NODE <= seekedProximity;

        if (closeEnough) {
            onArrive(status, traversal, agent);
            return;
        }

        Vector2 targetCell = getNextCell(traversal, agentPosition);
        continueMovement(traversal, status, agentPosition, targetCell);
    }

    private Vector2 getNextCell(TraverseComponent traversal, Vector2 agentPosition) {
        Vector2 targetCell;
        Vector2 currentCell = traversal.getCurrentCell();
        Vector2 nextCell = traversal.getNextCell();

        if (isAlignedWithNextCell(agentPosition, currentCell, nextCell)) {
            targetCell = nextCell;
            traversal.popCell();
        } else {
            targetCell = currentCell;
        }
        return targetCell;
    }

    private void continueMovement(TraverseComponent traversal, StatusComponent status, Vector2 agentPosition, Vector2 targetCell) {
        float differenceX = agentPosition.x - targetCell.x - HALF_METER;
        float differenceY = agentPosition.y - targetCell.y - HALF_METER;

        float deltaX = Math.abs(differenceX);
        float deltaY = Math.abs(differenceY);

        if (deltaX >= EPSILON || deltaY >= EPSILON) {
            Direction newDirection = resolveDirection(differenceX, differenceY, deltaX, deltaY);
            status.setDirection(newDirection);
            status.setAction(Action.WALKING);
        } else {
            traversal.popCell();
        }
    }

    private boolean isAlignedWithNextCell(Vector2 agentPosition, Vector2 currentCell, Vector2 nextCell) {
        Vector2 difference = new Vector2(nextCell).sub(currentCell);
        Vector2 absDifference = new Vector2(1 - Math.abs(difference.x), 1 - Math.abs(difference.y));

        float x1 = currentCell.x + absDifference.x * DELTA;
        float x2 = currentCell.x + METERS_PER_NODE - absDifference.x * DELTA;

        float y1 = currentCell.y + absDifference.y * DELTA;
        float y2 = currentCell.y + METERS_PER_NODE - absDifference.y * DELTA;

        return x1 <= agentPosition.x && agentPosition.x <= x2 && y1 <= agentPosition.y && agentPosition.y <= y2;
    }

    private void onArrive(StatusComponent status, TraverseComponent traversal, Entity agent) {
        status.setAction(Action.STANDING);
        agent.remove(TraverseComponent.class);
        traversal.arrive();
    }

    private Direction resolveHorizontalDirection(float differenceX) {
        if (differenceX > 0) {
            return Direction.LEFT;
        }

        return Direction.RIGHT;
    }

    private Direction resolveVerticalDirection(float differenceY) {
        if (differenceY > 0) {
            return Direction.DOWN;
        }

        return Direction.UP;
    }

    private Direction resolveDirection(float differenceX, float differenceY, float deltaX, float deltaY) {
        if (deltaX > EPSILON && deltaX > deltaY) {
            return resolveHorizontalDirection(differenceX);
        }

        return resolveVerticalDirection(differenceY);
    }
}
