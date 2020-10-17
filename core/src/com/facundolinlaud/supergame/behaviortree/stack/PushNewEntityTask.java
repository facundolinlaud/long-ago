package com.facundolinlaud.supergame.behaviortree.stack;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.services.EntityService;

/**
 * Pops: nothing
 * Pushes: the created entity value
 */
public class PushNewEntityTask extends LeafTask {
    @Override
    public void activate() {
        EntityService entityService = new EntityService();
        Entity e = entityService.create();
        stack.push(new Value(e));
        completed();
    }
}
