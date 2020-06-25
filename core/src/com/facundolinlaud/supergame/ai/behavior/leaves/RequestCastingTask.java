package com.facundolinlaud.supergame.ai.behavior.leaves;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Task;

/**
 * Pops: nothing
 * Pushes: nothing
 */
public class RequestCastingTask extends Task {
    private String skillId;

    public RequestCastingTask(String skillId) {
        this.skillId = skillId;
    }

    @Override
    public void activate() {
        Entity caster = getBlackboard().getAgent();
        boolean casted = getBlackboard().getSkillsManager().requestCasting(caster, skillId);

        if (casted) {
            System.out.println("[Casting] ok");
            completed();
        } else {
            System.out.println("[Casting] failed");
            failed();
        }
    }
}
