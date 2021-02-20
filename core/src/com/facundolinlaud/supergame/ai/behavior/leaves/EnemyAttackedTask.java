package com.facundolinlaud.supergame.ai.behavior.leaves;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.LeafTask;

/**
 * Pops: nothing
 * Pushes: an entity-value corresponding to the enemy that attacked the agent
 */
public class EnemyAttackedTask extends LeafTask {
    public EnemyAttackedTask() {
    }

    @Override
    public void activate() {
        Entity agent = getBlackboard().getAgent();
        failed();
    }
}
