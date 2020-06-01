package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

public class IdComponent implements Component {
    private String id;

    public IdComponent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
