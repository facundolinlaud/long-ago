package com.facundolinlaud.supergame.strategies.health;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.ai.BehaviorComponent;
import com.facundolinlaud.supergame.model.RenderPriority;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.AgentDiedEvent;
import com.facundolinlaud.supergame.utils.events.Messages;

public class NPCZeroHealthStrategyImpl implements ZeroHealthStrategy {
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<RenderComponent> rm = Mappers.render;

    @Override
    public void onZeroHealth(Entity agent) {
        StatusComponent statusComponent = sm.get(agent);
        statusComponent.setAction(Action.FALLING);

        removeGeneralComponents(agent);
        adjustRenderPriority(agent);

        broadcastAgentDead(agent);
    }

    private void removeGeneralComponents(Entity npc) {
        npc.remove(HealthComponent.class);
        npc.remove(BodyComponent.class);
        npc.remove(BehaviorComponent.class);
    }

    private void adjustRenderPriority(Entity agent) {
        RenderComponent renderComponent = rm.get(agent);
        renderComponent.setPriority(RenderPriority.DEAD_AGENT);
    }

    private void broadcastAgentDead(Entity agent) {
        AgentDiedEvent event = new AgentDiedEvent(agent);
        MessageManager.getInstance().dispatchMessage(Messages.AGENT_DIED, event);
    }
}
