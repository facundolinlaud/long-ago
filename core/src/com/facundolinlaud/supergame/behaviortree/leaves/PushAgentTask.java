package com.facundolinlaud.supergame.behaviortree.leaves;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.behaviortree.stack.Value;

public class PushAgentTask extends Task<Blackboard> {
    @Override
    public void activate() {
        Entity agent = getBlackboard().getAgent();
        Value value = new Value(agent);
        stack.push(value);

        completed();
    }
}
