package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.EntityAttackedEvent;
import com.facundolinlaud.supergame.utils.events.Messages;

public class CombatService extends Service {
    private ComponentMapper<HealthComponent> hm = Mappers.health;
    private MessageDispatcher messageDispatcher;

    public CombatService(Engine engine) {
        super(engine);
        this.messageDispatcher = MessageManager.getInstance();
    }

    public void affectAgent(Entity caster, Entity target, float amount) {
        HealthComponent healthComponent = hm.get(target);
        healthComponent.decrease(amount);
        broadcastEntityAttackedEvent(target, caster, amount);
    }

    private void broadcastEntityAttackedEvent(Entity target, Entity caster, Float damageDealt) {
        EntityAttackedEvent event = new EntityAttackedEvent(target, caster, damageDealt);
        messageDispatcher.dispatchMessage(Messages.ENTITY_ATTACKED, event);
    }
}
