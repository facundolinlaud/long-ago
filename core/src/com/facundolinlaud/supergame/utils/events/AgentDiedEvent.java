package com.facundolinlaud.supergame.utils.events;

import com.badlogic.ashley.core.Entity;

public class AgentDiedEvent extends Event {
    private Entity agent;

    public AgentDiedEvent(Entity agent) {
        this.agent = agent;
    }

    public Entity getAgent() {
        return agent;
    }
}
