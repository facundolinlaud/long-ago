package com.facundolinlaud.supergame.model.particle;

public enum ParticleType {
    NONE("NONE"),
    EXPLOSION("EXPLOSION");

    private String name;

    ParticleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
