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

import java.awt.*;

public class TraverseSystem extends IteratingSystem {
    private static final float EPSILON = 0.15f;
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

        float seekedProximity = Math.min(traversal.getSeekedProximity(), ONE_METER);
        boolean closeEnough = traversal.getPathLength() < seekedProximity;

        if (closeEnough) {
            onArrive(status, traversal, agent);
            return;
        }

        Point targetCell = traversal.getNextCell();
        Vector2 agentPosition = position.getPosition();

        float differenceX = agentPosition.x - targetCell.x - HALF_METER;
        float differenceY = agentPosition.y - targetCell.y - HALF_METER;

        float deltaX = Math.abs(differenceX);
        float deltaY = Math.abs(differenceY);

        if (deltaX > EPSILON || deltaY > EPSILON) {
            Direction newDirection = resolveDirection(differenceX, differenceY, deltaX, deltaY);
            status.setDirection(newDirection);
            status.setAction(Action.WALKING);
        } else {
            System.out.println("Popped cell " + targetCell + " at " + agentPosition + " with deltaX=" + differenceX + " and deltaY=" + differenceY);
            traversal.popCell();
        }
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
