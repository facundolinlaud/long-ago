package com.facundolinlaud.supergame.ai.decisionmaking;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.pathfinding.MapGraph;
import com.facundolinlaud.supergame.ai.pathfinding.Node;

import java.util.Map;

public class Blackboard {
    private Vector2 playerPosition;
    private Vector2 agentPosition;
    private MapGraph mapGraph;
    private Entity agent;

    public Blackboard(){}

    public Vector2 getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Vector2 playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Entity getAgent() {
        return agent;
    }

    public void setAgent(Entity agent) {
        this.agent = agent;
    }

    public Vector2 getAgentPosition() {
        return agentPosition;
    }

    public void setAgentPosition(Vector2 agentPosition) {
        this.agentPosition = agentPosition;
    }

    public MapGraph getMapGraph() {
        return mapGraph;
    }

    public void setMapGraph(MapGraph mapGraph) {
        this.mapGraph = mapGraph;
    }
}
