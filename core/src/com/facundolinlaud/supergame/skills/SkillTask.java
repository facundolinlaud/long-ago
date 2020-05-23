package com.facundolinlaud.supergame.skills;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.SequentialTask;
import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.LinkedList;

public class SkillTask extends SequentialTask<SkillBlackboard> {
    public SkillTask(LinkedList<Task> children) {
        super(children);
    }

    @Override
    public void activate() {
        super.activate();
    }

    @Override
    public void completed() {
        Entity caster = getBlackboard().getCaster();
        getBlackboard().getDomainManager().endCasting(caster);
    }
}
