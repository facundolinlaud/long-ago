package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

public class AgentComponent implements Component {
    private int id;

    public AgentComponent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
