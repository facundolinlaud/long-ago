package com.facundolinlaud.supergame.quests.listeners;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.facundolinlaud.supergame.components.AgentComponent;
import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.AgentDiedEvent;

import static com.facundolinlaud.supergame.utils.events.Messages.AGENT_DIED;

public class QuestSlayObjective extends QuestObjetive {
    private ComponentMapper<AgentComponent> am = Mappers.agent;
    private MessageDispatcher messageDispatcher;

    private int agentId;
    private int current;
    private int total;

    public QuestSlayObjective(Quest quest, String message, int agentId, int total) {
        super(quest, message);
        this.messageDispatcher = MessageManager.getInstance();
        this.agentId = agentId;
        this.total = total;
        this.current = 0;
    }

    @Override
    public void activate() {
        System.out.println("activating listener: " + getMessage());
        this.messageDispatcher.addListener(this, AGENT_DIED);
    }

    @Override
    public void deactivate() {
        this.messageDispatcher.removeListener(this, AGENT_DIED);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        if(msg.message == AGENT_DIED)
            onAgentDead((AgentDiedEvent) msg.extraInfo);

        return true;
    }

    private void onAgentDead(AgentDiedEvent event) {
        Entity agent = event.getAgent();
        if(am.has(agent)){
            AgentComponent agentComponent = am.get(agent);

            if(agentComponent.getId() == agentId){
                current++;
                System.out.println("    Agent[" + agentId + "] killed  (" + current + "/" + total + ")");
            }
        }

        if(current >= total){
            System.out.println("    Completed");
            complete();
        }
    }
}
