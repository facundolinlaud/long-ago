package com.facundolinlaud.supergame.model.agent;

public enum BehaviorType {
    PASSIVE("PASSIVE"),
    NEUTRAL("NEUTRAL"),
    AGGRESSIVE("AGGRESSIVE");

    private String behavior;

    BehaviorType(String behavior) {
        this.behavior = behavior;
    }
}
