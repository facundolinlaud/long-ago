package com.facundolinlaud.supergame.ai.decisionmaking2;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.AIBlackboard;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.PositionUtils;

/**
 * Pops: an entity-value representing the position where the agent will face towards
 * Pushes: nothing
 */
public class FacePositionTask extends Task<AIBlackboard> {
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    @Override
    public void activate() {
        Entity agent = getBlackboard().getAgent();
        Vector2 agentPosition = pm.get(agent).getPosition();
        Vector2 facingPosition = stack.pop().getPosition();

        Direction newDirection = PositionUtils.getFacingDirection(agentPosition, facingPosition);
        sm.get(agent).setDirection(newDirection);

        completed();
    }
}
