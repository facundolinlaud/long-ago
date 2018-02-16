package com.facundolinlaud.supergame.listeners;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.model.skill.AreaOfEffect;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.EntityAttackedEvent;
import com.facundolinlaud.supergame.utils.events.SkillCastEndEvent;
import com.facundolinlaud.supergame.utils.strategy.AreaOfEffectCheck;
import com.facundolinlaud.supergame.utils.strategy.impl.CircleAreaOfEffectCheck;
import com.facundolinlaud.supergame.utils.strategy.impl.SquareAreaOfEffectCheck;

import static com.facundolinlaud.supergame.utils.MessageCode.ENTITY_ATTACKED;
import static com.facundolinlaud.supergame.utils.MessageCode.SKILL_CAST_END;

public class SkillCastedListener implements Telegraph {
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<HealthComponent> hm = Mappers.health;

    private Engine engine;
    private MessageDispatcher messageDispatcher;

    public SkillCastedListener(Engine engine) {
        this.engine = engine;
        this.messageDispatcher = MessageManager.getInstance();
        this.messageDispatcher.addListener(this, SKILL_CAST_END);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        switch(msg.message){
            case SKILL_CAST_END:
                System.out.println("[HANDLING] SKILL_CAST_END");
                handleSkillCastEnd((SkillCastEndEvent) msg.extraInfo);
        }

        return true;
    }

    private void handleSkillCastEnd(SkillCastEndEvent e) {
        ImmutableArray<Entity> victims = engine.getEntitiesFor(Family.all(
                HealthComponent.class,
                PositionComponent.class).get());

        Entity caster = e.getCaster();

        AreaOfEffectCheck aoeCheck = buildAreaOfEffectChecker(
                e.getAreaOfEffect(),
                e.getAreaOfEffectSize(),
                e.getSkillEffectEpicenter());

        int skillDamage = e.getDamage();

        for(Entity victim : victims){
            if(victim != caster){
                PositionComponent victimPosition = pm.get(victim);

                if(aoeCheck.isInArea((int) victimPosition.x, (int) victimPosition.y)){
                    applyEffectsToVictim(victim, caster, skillDamage);
                }
            }
        }
    }

    private AreaOfEffectCheck buildAreaOfEffectChecker(AreaOfEffect aoe, int aoeSize, Vector2 pos){
        AreaOfEffectCheck aoeCheck;

        if(AreaOfEffect.CIRCLE.equals(aoe)){
            aoeCheck = new CircleAreaOfEffectCheck(pos, aoeSize);
        }else{
            aoeCheck = new SquareAreaOfEffectCheck(pos, aoeSize);
        }

        return aoeCheck;
    }

    private void applyEffectsToVictim(Entity victim, Entity caster, int damage) {
        HealthComponent healthComponent = hm.get(victim);
        healthComponent.health -= damage;
        System.out.println("Damaged enemy for " + damage + " leaving him with " + healthComponent.health + " hp left");

        EntityAttackedEvent event = new EntityAttackedEvent(victim, caster, damage);
        messageDispatcher.dispatchMessage(ENTITY_ATTACKED, event);
        System.out.println("[DISPATCHING] ENTITY_ATTACKED");
    }
}
