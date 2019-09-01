package com.facundolinlaud.supergame.components.ai;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.ai.BehaviorType;

public class AIComponent implements Component {
    private float viewDistance;
    private BehaviorType behaviorType;

    public AIComponent(BehaviorType behaviorType, float viewDistance) {
        this.behaviorType = behaviorType;
        this.viewDistance = viewDistance;
    }

    public float getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(float viewDistance) {
        this.viewDistance = viewDistance;
    }

    public BehaviorType getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(BehaviorType behaviorType) {
        this.behaviorType = behaviorType;
    }
}
