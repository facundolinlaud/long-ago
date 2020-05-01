package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.skills.SkillBlackboard;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.shape.Shape;

import java.util.List;
import java.util.Stack;

/**
 * Pops: nothing
 * Pushes: n+1 values where:
 *  The first n are entity-values
 *  The n+1 value is an integer-value of value n
 */
public class PushAgentsInAreaTask extends Task<SkillBlackboard> {
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private Shape shape;
    private float offset;
    private boolean ignoreCaster;

    public PushAgentsInAreaTask(Shape shape, float offset, boolean ignoreCaster) {
        this.shape = shape;
        this.offset = offset;
        this.ignoreCaster = ignoreCaster;
    }

    @Override
    public void activate() {
        System.out.println("Activating PushAgentsInArea");

        Entity caster = getBlackboard().getCaster();

        Vector2 casterPosition = getCasterPosition(caster);
        Direction casterDirection = getCasterDirection(caster);

        shape.setPosition(casterPosition);
        shape.traslate(casterDirection, offset);

        List<Entity> agents = getBlackboard().getAgentsService().in(shape);

        if(ignoreCaster && agents.contains(caster))
            agents.remove(caster);

        pushAgentsToStack(agents);
        completed();
    }

    private Vector2 getCasterPosition(Entity caster){
        return pm.get(caster).getPosition();
    }

    private Direction getCasterDirection(Entity caster){
        return sm.get(caster).getDirection();
    }

    private void pushAgentsToStack(List<Entity> agents){
        Stack<Value> stack = getBlackboard().getStack();
        agents.forEach(agent -> stack.push(new Value(agent)));
        stack.push(new Value(agents.size()));
    }

    @Override
    public void completed() {
        System.out.println("Completing PushAgentsInArea");
        super.completed();
    }
}
