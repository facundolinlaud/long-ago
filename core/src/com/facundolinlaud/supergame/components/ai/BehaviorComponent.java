package com.facundolinlaud.supergame.components.ai;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.ai.BehaviorType;

public class BehaviorComponent implements Component {
    private String faction;
    private float viewDistance;
    private BehaviorType behaviorType;

    public BehaviorComponent(String faction, float viewDistance, BehaviorType behaviorType) {
        this.faction = faction;
        this.viewDistance = viewDistance;
        this.behaviorType = behaviorType;
    }

    public BehaviorComponent(float viewDistance, BehaviorType behaviorType) {
        this.viewDistance = viewDistance;
        this.behaviorType = behaviorType;
    }

    public float getViewDistance() {
        return viewDistance;
    }

    public BehaviorType getBehaviorType() {
        return behaviorType;
    }

    public String getFaction() {
        return faction;
    }
}
