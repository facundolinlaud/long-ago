package com.facundolinlaud.supergame.model.skill;

public enum SkillType {
    SPELL("SPELL"),
    NORMAL("NORMAL"),
    PROJECTILE("PROJECTILE");


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
