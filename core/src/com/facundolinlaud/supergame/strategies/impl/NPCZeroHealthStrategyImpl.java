package com.facundolinlaud.supergame.strategies.impl;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.strategies.ZeroHealthStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

public class NPCZeroHealthStrategyImpl implements ZeroHealthStrategy {
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    public NPCZeroHealthStrategyImpl() { }

    @Override
    public void onZeroHealth(Entity npc) {
        StatusComponent statusComponent = sm.get(npc);
        statusComponent.setAction(Action.FALLING);

        npc.remove(HealthComponent.class);
        npc.remove(BodyComponent.class);

        System.out.println("NPC died");
    }
}
