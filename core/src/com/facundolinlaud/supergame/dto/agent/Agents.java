package com.facundolinlaud.supergame.dto.agent;

import java.util.Map;

public class Agents {
    private Map<Integer, Agent> agents;

    public Agents() {
    }

    public Map<Integer, Agent> getAgents() {
        return agents;
    }

    public void setAgents(Map<Integer, Agent> agents) {
        this.agents = agents;
    }

    @Override
    public String toString() {
        return "Agents{" +
                "agents=" + agents +
                '}';
    }
}
