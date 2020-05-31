package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.skills.SkillBlackboard;

public class PushCasterTask extends Task<SkillBlackboard> {
    @Override
    public void activate() {
        Entity caster = getBlackboard().getCaster();
        Value value = new Value(caster);
        stack.push(value);
        
        completed();
    }
}
