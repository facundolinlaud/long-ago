package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.skills.SkillBlackboard;

/**
 * Pops: an agent entity-value
 * Pushes: nothing
 */
public class AffectAgentHealthTask extends Task<SkillBlackboard> {
    private float amount;

    public AffectAgentHealthTask(float amount) {
        this.amount = amount;
    }

    @Override
    public void activate() {
        System.out.println("Activating AffectAgentHealth");

        SkillBlackboard blackboard = getBlackboard();

        CombatService combatService = blackboard.getCombatService();
        Entity target = blackboard.popEntity();
        Entity caster = blackboard.getCaster();

        combatService.affectAgent(caster, target, amount);
        completed();
    }

    @Override
    public void completed() {
        System.out.println("Completing AffectAgentHealth");
        super.completed();
    }
}
