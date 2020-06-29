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
        Runnable onSkillEnd = () -> {
            System.out.println("[Casting] completed");
            completed();
        };

        System.out.println("[Casting] casting");
        boolean casted = getBlackboard().getSkillsManager().requestCasting(caster, skillId, onSkillEnd);

        if (!casted) {
            System.out.println("[Casting] failed");
            failed();
        }
    }
}
