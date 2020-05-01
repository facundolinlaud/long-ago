package com.facundolinlaud.supergame.skills;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.SequentialTask;
import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.LinkedList;

public class Skill extends SequentialTask<SkillBlackboard> {
    private String id;

    public Skill(String id, LinkedList<Task> children) {
        super(children);
        this.id = id;
    }

    @Override
    public void activate() {
        System.out.println("Activating skill");
        super.activate();
    }

    @Override
    public void completed() {
        System.out.println("Completing skill");
        Entity caster = getBlackboard().getCaster();
        getBlackboard().getDomainManager().endCasting(caster);
    }
}
