package com.facundolinlaud.supergame.quests.leaves;

import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.behaviortree.Task;

import static com.facundolinlaud.supergame.utils.events.Messages.PLAYER_INTERACTION;

public class InteractionTask extends Task implements Telegraph {
    private String agentId;

    public InteractionTask(String agentId) {
        this.agentId = agentId;
    }

    @Override
    public void activate() {
        subscribeToEvents();
    }

    private void subscribeToEvents() {
        MessageManager.getInstance().addListener(this, PLAYER_INTERACTION);
    }

    private void unsubscribeToEvents() {
        MessageManager.getInstance().removeListener(this, PLAYER_INTERACTION);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        String eventAgentId = (String) msg.extraInfo;

        if (eventAgentId == agentId) {
            unsubscribeToEvents();
            super.completed();
        }

        return false;
    }
}
