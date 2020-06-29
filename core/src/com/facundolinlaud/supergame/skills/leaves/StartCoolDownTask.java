package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.skills.SkillBlackboard;

/**
 * Pops: nothing
 * Pushes: nothing
 */
public class StartCoolDownTask extends LeafTask<SkillBlackboard> {
    @Override
    public void activate() {
        SkillBlackboard blackboard = getBlackboard();

        Entity caster = blackboard.getAgent();
        blackboard.getDomainTaskManager().startCoolDown(caster);

        completed();
    }
}
