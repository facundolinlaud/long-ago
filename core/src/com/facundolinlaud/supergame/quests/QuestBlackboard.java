package com.facundolinlaud.supergame.quests;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Blackboard;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.managers.world.QuestsManager;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;

public class QuestBlackboard extends Blackboard {
    private Entity player;
    private DialogUIController dialogUIController;
    private AgentService agentService;
    private Engine engine;
    private QuestsManager questsManager;

    public QuestBlackboard(Entity player, DialogUIController dialogUIController, AgentService agentService,
                           Engine engine, QuestsManager questsManager) {
        this.player = player;
        this.dialogUIController = dialogUIController;
        this.agentService = agentService;
        this.engine = engine;
        this.questsManager = questsManager;
    }

    public Entity getPlayer() {
        return player;
    }

    public DialogUIController getDialogUIController() {
        return dialogUIController;
    }

    public Engine getEngine() {
        return engine;
    }

    public AgentService getAgentService() {
        return agentService;
    }

    @Override
    public PoolableTaskManager getDomainManager() {
        return this.questsManager;
    }
}
