package com.facundolinlaud.supergame.quests;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.factory.AgentFactory;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

public class Blackboard {
    private Entity player;
    private DialogUIController dialogUIController;
    private AgentFactory agentFactory;
    private Engine engine;

    public Blackboard(Entity player, DialogUIController dialogUIController, AgentFactory agentFactory, Engine engine) {
        this.player = player;
        this.dialogUIController = dialogUIController;
        this.agentFactory = agentFactory;
        this.engine = engine;
    }

    public Entity getPlayer() {
        return player;
    }

    public DialogUIController getDialogUIController() {
        return dialogUIController;
    }

    public AgentFactory getAgentFactory() {
        return agentFactory;
    }

    public Engine getEngine() {
        return engine;
    }
}
