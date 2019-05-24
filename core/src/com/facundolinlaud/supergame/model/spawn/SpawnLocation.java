package com.facundolinlaud.supergame.model.spawn;

import com.badlogic.gdx.math.Rectangle;

public class SpawnLocation {
    private int agentID;
    private int agentsCount;
    private Rectangle rectangle;

    public SpawnLocation(int agentID, int agentsCount, Rectangle rectangle) {
        this.agentID = agentID;
        this.agentsCount = agentsCount;
        this.rectangle = rectangle;
    }

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }

    public int getAgentsCount() {
        return agentsCount;
    }

    public void setAgentsCount(int agentsCount) {
        this.agentsCount = agentsCount;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
