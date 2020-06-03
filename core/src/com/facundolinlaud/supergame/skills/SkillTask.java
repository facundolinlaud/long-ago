package com.facundolinlaud.supergame.skills;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.SequentialTask;
import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.LinkedList;
import java.util.Stack;

public class SkillTask extends SequentialTask<SkillBlackboard> {
    public SkillTask(LinkedList<Task> children) {
        super(children);
        setStack(new Stack());
    }

    @Override
    public void activate() {
        super.activate();
    }

    @Override
    public void failed() {
        completed();
    }

    @Override
    public void completed() {
        Entity caster = getBlackboard().getCaster();
        getBlackboard().getDomainManager().endCasting(caster);
    }
}
