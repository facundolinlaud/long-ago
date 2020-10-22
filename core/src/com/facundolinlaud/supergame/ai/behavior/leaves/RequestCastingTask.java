package com.facundolinlaud.supergame.ai.behavior.leaves;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.managers.world.SkillsManager;

/**
 * Pops: nothing
 * Pushes: nothing
 */
public class RequestCastingTask extends LeafTask {
    private SkillsManager skillsManager;

    private String skillId;

    public RequestCastingTask(String skillId) {
        this.skillId = skillId;
    }

    @Override
    protected void onBlackboardAvailable(Blackboard blackboard) {
        skillsManager = blackboard.getSkillsManager();
    }

    @Override
    public void activate() {
        Entity caster = getBlackboard().getAgent();
        Runnable onSkillEnd = () -> completed();

        boolean casted = skillsManager.requestCasting(caster, skillId, onSkillEnd);

        if (!casted) {
            failed();
        }
    }

    @Override
    protected void postAbort() {
        Entity agent = getBlackboard().getAgent();
        skillsManager.abort(agent);
    }
}
