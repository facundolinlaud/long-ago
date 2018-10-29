package com.facundolinlaud.supergame.ai;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class Blackboard {
    private Vector2 playerPosition;
    private Vector2 agentPosition;
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

    public void patrol(){
        System.out.println("patrolling");
    }

    public void attack(){
        System.out.println("attacking");
    }
}
