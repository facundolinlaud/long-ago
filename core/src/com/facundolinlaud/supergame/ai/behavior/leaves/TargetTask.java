package com.facundolinlaud.supergame.ai.behavior.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.behavior.BehaviorBlackboard;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.components.TargetComponent;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Pops: an entity-value representing the position where the agent will face towards
 * Pushes: nothing
 */
public class TargetTask extends Task<BehaviorBlackboard> {
    private ComponentMapper<TargetComponent> tm = Mappers.target;

    @Override
    public void activate() {
        Vector2 position = stack.pop().getPosition();

        Entity agent = getBlackboard().getAgent();
        TargetComponent targetComponent = tm.get(agent);
        targetComponent.setPosition(position);
        targetComponent.setClicking(true);

        completed();
    }
}
