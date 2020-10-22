package com.facundolinlaud.supergame.ai.behavior.leaves;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.ai.behavior.BehaviorBlackboard;
import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.components.ai.PursueComponent;

/**
 * Pops: an entity-value corresponding to the pursuable entity
 * Pushes: nothing
 */
public class PursueTask extends LeafTask<BehaviorBlackboard> {
    private float seekedProximity;

    public PursueTask(float seekedProximity) {
        this.seekedProximity = seekedProximity;
    }

    @Override
    public void activate() {
        Entity agent = getBlackboard().getAgent();
        Entity pursuable = stack.pop().getEntity();
        Runnable onArrive = () -> completed();
        Runnable onFail = () -> failed();

        agent.add(new PursueComponent(pursuable, seekedProximity, onArrive, onFail));
    }
}
