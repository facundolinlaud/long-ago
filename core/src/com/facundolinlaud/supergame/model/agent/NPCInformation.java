package com.facundolinlaud.supergame.model.agent;

import com.facundolinlaud.supergame.model.ai.BehaviorType;

import java.util.List;

public class NPCInformation {
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
