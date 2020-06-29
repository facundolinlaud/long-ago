package com.facundolinlaud.supergame.behaviortree.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Pops: an agent-value
 * Pushes: a position-value corresponding to the agent position
 */
public class PushEntityPositionTask extends LeafTask<Blackboard> {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    @Override
    public void activate() {
        Entity entity = stack.pop().getEntity();
        PositionComponent positionComponent = pm.get(entity);

        Value value = new Value(positionComponent.getPosition());
        stack.push(value);

        completed();
    }
}
