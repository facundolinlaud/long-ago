package com.facundolinlaud.supergame.utils.events;

import com.badlogic.ashley.core.Entity;

public class AgentDiedEvent extends Event {
    private boolean handled;
    private Entity agent;

    public AgentDiedEvent(Entity agent) {
        this.agent = agent;
    }

    public Entity getAgent() {
        return agent;
    }

    public boolean wasHandled(){
        return this.handled;
    }

    public void setHandled(){
        this.handled = true;
    }
}
