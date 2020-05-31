package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.skills.SkillBlackboard;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Pops: an agent-value
 * Pushes: two float-values corresponding to the x and y agent position values respectively
 */
public class PushEntityPositionTask extends Task<SkillBlackboard> {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    @Override
    public void activate() {
        Entity entity = stack.pop().getEntity();
        PositionComponent positionComponent = pm.get(entity);

        stack.push(new Value(positionComponent.x));
        stack.push(new Value(positionComponent.y));

        completed();
    }

    @Override
    public void completed() {
        super.completed();
    }
}
