package com.facundolinlaud.supergame.ai.decisionmaking2;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.AIBlackboard;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.components.TargetComponent;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Pops: two float-values corresponding to the x and y target position values respectively
 * Pushes: nothing
 */
public class TargetTask extends Task<AIBlackboard> {
    private ComponentMapper<TargetComponent> tm = Mappers.target;

    @Override
    public void activate() {
        float y = stack.pop().getFloat();
        float x = stack.pop().getFloat();
        Vector2 position = new Vector2(x, y);

        Entity agent = getBlackboard().getAgent();
        TargetComponent targetComponent = tm.get(agent);
        targetComponent.setPosition(position);

        completed();
    }
}
