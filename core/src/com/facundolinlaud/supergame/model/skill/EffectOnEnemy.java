package com.facundolinlaud.supergame.model.skill;

public enum EffectOnEnemy {
    KNOCK_BACK("KNOCK_BACK"),
    STUN("STUN"),
    SLEEP("SLEEP");

    private String name;

    EffectOnEnemy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
