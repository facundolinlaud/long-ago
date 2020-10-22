package com.facundolinlaud.supergame.skills;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.composites.SequentialTask;

import java.util.LinkedList;
import java.util.Stack;

public class SkillTask extends SequentialTask<SkillBlackboard> {
    public SkillTask(LinkedList<Task> children) {
        super(children);
        setStack(new Stack());
    }

    @Override
    public void failed() {
        completed();
    }

    @Override
    public void completed() {
        SkillBlackboard blackboard = getBlackboard();
        Entity caster = blackboard.getAgent();
        blackboard.getDomainTaskManager().endCasting(caster);
    }
}
