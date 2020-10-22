package com.facundolinlaud.supergame.utils;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public abstract class PhasedIteratingSystem extends EntitySystem {
    private Family family;
    private ImmutableArray<Entity> entities;

    public PhasedIteratingSystem(Family family) {
        this.family = family;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void update(float deltaTime) {
        if(entities.size() == 0) return;

        beforeFrame();
        entities.forEach(e -> processEntity(e, deltaTime));
        afterFrame();
    }

    protected void beforeFrame() {
    }

    protected void afterFrame() {
    }

    protected abstract void processEntity(Entity entity, float deltaTime);
}
