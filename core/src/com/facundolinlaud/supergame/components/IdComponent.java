package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

public class IdComponent implements Component {
    private int id;

    public IdComponent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
