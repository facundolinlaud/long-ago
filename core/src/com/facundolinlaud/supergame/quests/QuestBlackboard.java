package com.facundolinlaud.supergame.quests;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Blackboard;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.factory.AgentFactory;
import com.facundolinlaud.supergame.managers.world.QuestsManager;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

public class QuestBlackboard extends Blackboard {
    private Entity player;
    private DialogUIController dialogUIController;
    private AgentFactory agentFactory;
    private Engine engine;
    private QuestsManager questsManager;

    public QuestBlackboard(Entity player, DialogUIController dialogUIController, AgentFactory agentFactory,
                           Engine engine, QuestsManager questsManager) {
        this.player = player;
        this.dialogUIController = dialogUIController;
        this.agentFactory = agentFactory;
        this.engine = engine;
        this.questsManager = questsManager;
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

    @Override
    public PoolableTaskManager getDomainManager() {
        return this.questsManager;
    }
}
