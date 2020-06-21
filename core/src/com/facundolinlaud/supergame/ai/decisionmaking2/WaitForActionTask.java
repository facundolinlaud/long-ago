package com.facundolinlaud.supergame.ai.decisionmaking2;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.PoolableTask;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Pops: nothing
 * Pushes: nothing
 */
public class WaitForActionTask extends PoolableTask {
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private Entity player;
    private Action action;

    public WaitForActionTask(Action action) {
        this.action = action;
    }

    @Override
    protected void onBlackboardAvailable(Blackboard blackboard) {
        player = getBlackboard().getAgent();
    }

    @Override
    public void tick(float delta) {
        StatusComponent statusComponent = sm.get(player);

        if (statusComponent.getAction().equals(action)) {
            completed();
        }
    }
}
