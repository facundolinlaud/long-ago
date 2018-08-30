package com.facundolinlaud.supergame.model.skill;

public enum AnimationFlow {
    CAST_ON_FIRST_PHOTOGRAM_THEN_FLOW("CAST_ON_FIRST_PHOTOGRAM_THEN_FLOW"),
    TRAVERSE("TRAVERSE"),
    STOP_AT_LAST_PHOTOGRAM("STOP_AT_LAST_PHOTOGRAM");

    private String name;

    AnimationFlow(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
