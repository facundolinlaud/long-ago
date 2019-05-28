package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.skills.SkillTargetedComponent;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.stats.DamageLogic;
import com.facundolinlaud.supergame.strategies.stats.DefaultDamageLogic;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.Messages;
import com.facundolinlaud.supergame.utils.events.EntityAttackedEvent;

public class SkillTargetedSystem extends IteratingSystem {
    private ComponentMapper<SkillTargetedComponent> stm = Mappers.skillTargeted;
    private ComponentMapper<HealthComponent> hm = Mappers.health;

    private MessageDispatcher messageDispatcher;
    private DamageLogic damageLogic;

    public SkillTargetedSystem() {
        super(Family.all(SkillTargetedComponent.class, HealthComponent.class).get());
        this.damageLogic = new DefaultDamageLogic();
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    protected void processEntity(Entity target, float deltaTime) {
        SkillTargetedComponent skillTargetedComponent = stm.get(target);
        HealthComponent healthComponent = hm.get(target);

        Entity caster = skillTargetedComponent.caster;
        Skill skill = skillTargetedComponent.skill;

        Float damageDealt = damageLogic.calculateDamageDealt(caster, target, skill);
        healthComponent.decrease(damageDealt);

        target.remove(SkillTargetedComponent.class);

        broadcastEntityAttackedEvent(target, caster, damageDealt);
    }

    private void broadcastEntityAttackedEvent(Entity target, Entity caster, Float damageDealt) {
        EntityAttackedEvent event = new EntityAttackedEvent(target, caster, damageDealt);
        messageDispatcher.dispatchMessage(Messages.ENTITY_ATTACKED, event);
    }
}
