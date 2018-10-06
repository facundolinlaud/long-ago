package com.facundolinlaud.supergame.model.light;

public enum LightType {
    POINT("POINT"),
    FLICKERING("FLICKERING"),
    DIMMING("DIMMING");

    private String name;

    LightType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
