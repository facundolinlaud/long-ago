package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.model.skill.Skill;

public class ProjectileComponent implements Component {
    private Entity caster;
    private Skill skill;
    private Vector2 lastPosition;
    private float distanceToTravel;

    public ProjectileComponent(Entity caster, Skill skill, Vector2 origin) {
        this.caster = caster;
        this.skill = skill;
        this.lastPosition = origin;
        this.distanceToTravel = skill.getProjectileInformation().getMaxTravelDistance();
    }

    public Entity getCaster() {
        return caster;
    }

    public Skill getSkill() {
        return skill;
    }

    public void travel(Vector2 newPosition){
        float distanceTravelled = lastPosition.dst(newPosition);
        this.distanceToTravel -= distanceTravelled;
        this.lastPosition = newPosition;
    }

    public boolean hasTravelDistanceMaxedOut(){
        return this.distanceToTravel <= 0;
    }
}
