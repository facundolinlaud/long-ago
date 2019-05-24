package com.facundolinlaud.supergame.components.spawn;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.facundolinlaud.supergame.model.spawn.SpawnLocation;

public class SpawnLocationComponent implements Component {
    private int agentID;
    private int agentsCount;
    private Rectangle rectangle;

    public SpawnLocationComponent(SpawnLocation s) {
        this.agentID = s.getAgentID();
        this.agentsCount = s.getAgentsCount();
        this.rectangle = s.getRectangle();
    }

    public int getAgentID() {
        return agentID;
    }

    public int getAgentsCount() {
        return agentsCount;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
