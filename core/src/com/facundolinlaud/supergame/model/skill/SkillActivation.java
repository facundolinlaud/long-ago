package com.facundolinlaud.supergame.model.skill;

public enum SkillActivation {
    INSTANT("INSTANT"),
    ON_ENTITY_HIT("ON_ENTITY_HIT");

    private String name;

    SkillActivation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
