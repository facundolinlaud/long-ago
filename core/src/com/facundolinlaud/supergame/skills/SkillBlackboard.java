package com.facundolinlaud.supergame.skills;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Blackboard;
import com.facundolinlaud.supergame.behaviortree.TaskPoolableManager;

public class SkillBlackboard implements Blackboard {
    private Entity caster;

    public SkillBlackboard(Entity caster) {
        this.caster = caster;
    }

    public Entity getCaster() {
        return caster;
    }

    @Override
    public TaskPoolableManager getDomainManager() {
        return null;
    }
}
