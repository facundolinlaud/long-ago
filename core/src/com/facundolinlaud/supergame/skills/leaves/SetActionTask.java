package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.skills.SkillBlackboard;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Pops: nothing
 * Pushes: nothing
 */
public class SetActionTask extends Task<SkillBlackboard> {
    private Action action;

    public SetActionTask(Action action) {
        this.action = action;
    }

    @Override
    public void activate() {
        Entity caster = getBlackboard().getCaster();
        StatusComponent statusComponent = Mappers.status.get(caster);
        statusComponent.setAction(action);

        completed();
    }
}
