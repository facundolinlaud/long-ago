package com.facundolinlaud.supergame.dto.agent;

import com.facundolinlaud.supergame.model.ai.BehaviorType;

public class AIInformation {
    private float viewDistance;
    private BehaviorType behaviorType;

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
