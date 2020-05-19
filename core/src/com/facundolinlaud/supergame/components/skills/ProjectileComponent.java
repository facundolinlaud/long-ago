package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class ProjectileComponent implements Component {
    private Entity caster;
    private Vector2 lastPosition;
    private float distanceToTravel;

    public ProjectileComponent(Entity caster, float maxTravelDistance, Vector2 origin) {
        this.caster = caster;
        this.lastPosition = origin;
        this.distanceToTravel = maxTravelDistance;
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
}
