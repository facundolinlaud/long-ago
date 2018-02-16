package com.facundolinlaud.supergame.model.skill;

public enum AreaOfEffect {
    SQUARE("SQUARE"),
    CIRCLE("CIRCLE"),
    LINE("LINE");

    private String name;

    AreaOfEffect(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
