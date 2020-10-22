package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

public class TagComponent implements Component {
    private String id;

    public TagComponent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
