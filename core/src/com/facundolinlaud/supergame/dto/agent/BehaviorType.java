package com.facundolinlaud.supergame.dto.agent;

public enum BehaviorType {
    PASSIVE("PASSIVE"),
    NEUTRAL("NEUTRAL"),
    AGGRESSIVE("AGGRESSIVE");

    private String behavior;

    BehaviorType(String behavior) {
        this.behavior = behavior;
    }
}
