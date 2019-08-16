package com.facundolinlaud.supergame.quests.leafs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.components.IdComponent;
import com.facundolinlaud.supergame.quests.Task;
import com.facundolinlaud.supergame.utils.Debugger;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.AgentDiedEvent;

import static com.facundolinlaud.supergame.utils.events.Messages.AGENT_DIED;

public class SlayTask extends Task implements Telegraph {
    private ComponentMapper<IdComponent> idm = Mappers.id;
    private MessageDispatcher messageDispatcher;
    private int agentId;
    private int current;
    private int total;

    public SlayTask(int agentId, int total) {
        this.messageDispatcher = MessageManager.getInstance();
        this.agentId = agentId;
        this.total = total;
        this.current = 0;
    }

    @Override
    public void activate() {
        messageDispatcher.addListener(this, AGENT_DIED);
        Debugger.debug("[SLAY" + agentId + "] Activating to kill " + total + " of agent id " + agentId);
    }

    @Override
    public void completed() {
        messageDispatcher.removeListener(this, AGENT_DIED);
        Debugger.debug("[SLAY" + agentId + "] Completed");
        super.completed();
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        if(msg.message == AGENT_DIED) {
            AgentDiedEvent e = (AgentDiedEvent) msg.extraInfo;
            Debugger.debug("    [SLAY" + agentId + "] Received death");

            if(!e.wasHandled()){
                Debugger.debug("    [SLAY" + agentId + "] Handling");
                onAgentDead(e);
            }else{
                Debugger.debug("    [SLAY" + agentId + "] Not handling");
            }
        }

        return true;
    }

    private void onAgentDead(AgentDiedEvent event) {
        Entity agent = event.getAgent();
        if(idm.has(agent)){
            IdComponent agentComponent = idm.get(agent);

            if(agentComponent.getId() == agentId){
                event.setHandled();
                current++;
                Debugger.debug("    [SLAY" + agentId + "] Agent(" + agentId + ") killed  (" + current + "/" + total + ")");
            }
        }

        if(current >= total){
            completed();
        }
    }
}
