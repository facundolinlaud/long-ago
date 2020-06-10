package com.facundolinlaud.supergame.behaviortree.leaves;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Pops: an agent entity-value
 * Pushes: nothing
 */
public class SetActionTask extends Task<Blackboard> {
    private Action action;

    public SetActionTask(Action action) {
        this.action = action;
    }

    @Override
    public void activate() {
        Entity agent = stack.pop().getEntity();
        StatusComponent statusComponent = Mappers.status.get(agent);
        statusComponent.setAction(action);

        completed();
    }
}
