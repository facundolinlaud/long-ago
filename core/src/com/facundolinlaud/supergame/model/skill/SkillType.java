package com.facundolinlaud.supergame.model.skill;

public enum SkillType {
    MELEE_SKILL("MELEE_SKILL"),
    RANGED_SKILL("RANGED_SKILL");

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
