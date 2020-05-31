package com.facundolinlaud.supergame.model.skill;

public enum SkillType {
    SPELL("SPELL"),
    MELEE("MELEE"),
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

    public boolean isTwoClick(){
        return this == SPELL || this == PROJECTILE;
    }
}
