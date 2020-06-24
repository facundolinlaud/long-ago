package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.skills.SkillBlackboard;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.shape.Shape;

import java.util.List;

/**
 * Pops: a position-value corresponding to the area center respectively
 * Pushes: n+1 values where:
 * The first n are entity-values
 * The n+1 value is an integer-value of value n
 * <p>
 * If offset is null, then no traslation with the caster's direction will be performed
 */
public class PushAgentsInAreaTask extends Task<SkillBlackboard> {
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private Shape shape;
    private Float offset;
    private boolean ignoreCaster;

    public PushAgentsInAreaTask(Shape shape, Float offset, boolean ignoreCaster) {
        this.shape = shape;
        this.offset = offset;
        this.ignoreCaster = ignoreCaster;
    }

    @Override
    public void activate() {
        Vector2 areaPosition = stack.pop().getPosition();

        Entity caster = getBlackboard().getAgent();

        shape.setPosition(areaPosition);

        if (offset != null) {
            Direction casterDirection = getCasterDirection(caster);
            shape.traslate(casterDirection, offset);
        }

        List<Entity> agents = getBlackboard().getAgentService().in(shape);

        if (ignoreCaster && agents.contains(caster))
            agents.remove(caster);

        pushAgentsToStack(agents);
        completed();
    }

    private Direction getCasterDirection(Entity caster) {
        return sm.get(caster).getDirection();
    }

    private void pushAgentsToStack(List<Entity> agents) {
        agents.forEach(agent -> stack.push(new Value(agent)));
        stack.push(new Value(agents.size()));
    }
}
