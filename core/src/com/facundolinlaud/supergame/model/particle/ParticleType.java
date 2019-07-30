package com.facundolinlaud.supergame.model.particle;

public enum ParticleType {
    NONE("NONE"),
    EXPLOSION("EXPLOSION"),
    BLACK_SMOKE("BLACK_SMOKE"),
    FIRE_PROJECTILE("FIRE_PROJECTILE"),
    BLUE_HIT("BLUE_HIT"),
    BLUE_VORTERIX("BLUE_VORTERIX");

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
