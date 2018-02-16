package com.facundolinlaud.supergame.model.skill;

public enum SkillTrigger {
    KEY_PRES("KEY_PRES"),
    KEY_PRESS_THEN_CLICK("KEY_PRESS_THEN_CLICK");

    private String name;

    SkillTrigger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
