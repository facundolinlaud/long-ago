package com.facundolinlaud.supergame.model.skill;

public enum DamageType {
    DIRECT("DIRECT"),
    DOT("DOT");

    private String name;

    DamageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
