package com.facundolinlaud.supergame.quests.leaves;

import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.behaviortree.Task;

import static com.facundolinlaud.supergame.utils.events.Messages.PLAYER_INTERACTION;

public class InteractionTask extends Task implements Telegraph {
    private String agentTag;

    public InteractionTask(String agentTag) {
        this.agentTag = agentTag;
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

        if (eventAgentId == agentTag) {
            unsubscribeToEvents();
            super.completed();
        }

        return false;
    }
}
