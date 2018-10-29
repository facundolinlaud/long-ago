package com.facundolinlaud.supergame.strategies.health;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastedComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.components.skills.SkillLockdownComponent;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.utils.Mappers;

public class NPCZeroHealthStrategyImpl implements ZeroHealthStrategy {
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    public NPCZeroHealthStrategyImpl() { }

    @Override
    public void onZeroHealth(Entity npc) {
        StatusComponent statusComponent = sm.get(npc);
        statusComponent.setAction(Action.FALLING);

        removeGeneralComponents(npc);
        removeSkillCastingComponents(npc);

        System.out.println("NPC died");
    }

    private void removeGeneralComponents(Entity npc) {
        npc.remove(HealthComponent.class);
        npc.remove(BodyComponent.class);
        npc.remove(AIComponent.class);
    }

    private void removeSkillCastingComponents(Entity npc){
        npc.remove(SkillCastingComponent.class);
        npc.remove(SkillCastedComponent.class);
        npc.remove(SkillLockdownComponent.class);
    }
}
