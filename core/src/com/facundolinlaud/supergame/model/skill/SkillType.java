package com.facundolinlaud.supergame.model.skill;

public enum SkillType {
    MELEE("MELEE"),
    RANGED("RANGED");

    private String name;

    SkillType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
