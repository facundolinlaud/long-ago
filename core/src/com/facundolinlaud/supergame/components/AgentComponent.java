package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

public class AgentComponent implements Component {
    private String id;
    private String factionId;

    public AgentComponent(String id, String factionId) {
        this.id = id;
        this.factionId = factionId;
    }

    public String getId() {
        return id;
    }

    public String getFactionId() {
        return factionId;
    }
}
