package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import java.util.function.Consumer;

public class ProjectileComponent implements Component {
    private Entity caster;
    private Vector2 lastPosition;
    private float distanceToTravel;
    private Consumer<Entity> onHit;
    private Runnable onMiss;

    public ProjectileComponent(Entity caster, float maxTravelDistance,
                               Vector2 origin, Consumer<Entity> onHit, Runnable onMiss) {
        this.caster = caster;
        this.lastPosition = origin;
        this.distanceToTravel = maxTravelDistance;
        this.onHit = onHit;
        this.onMiss = onMiss;
    }

    public Entity getCaster() {
        return caster;
    }

    public void travel(Vector2 newPosition) {
        float distanceTravelled = lastPosition.dst(newPosition);
        this.distanceToTravel -= distanceTravelled;
        this.lastPosition = newPosition;
    }

    public boolean hasTravelDistanceMaxedOut() {
        return this.distanceToTravel <= 0;
    }

    public Consumer<Entity> getOnHit() {
        return onHit;
    }

    public Runnable getOnMiss() {
        return onMiss;
    }
}
