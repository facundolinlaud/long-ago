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
import com.badlogic.gdx.utils.Timer;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.model.skill.AreaOfEffect;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.EntityAttackedEvent;
import com.facundolinlaud.supergame.utils.events.SkillCastEndEvent;
import com.facundolinlaud.supergame.strategies.AreaOfEffectCheckStrategy;
import com.facundolinlaud.supergame.strategies.impl.CircleAreaOfEffectCheckStrategyStrategyImpl;
import com.facundolinlaud.supergame.strategies.impl.SquareAreaOfEffectCheckStrategyStrategyImpl;

import static com.facundolinlaud.supergame.utils.MessageCode.ENTITY_ATTACKED;
import static com.facundolinlaud.supergame.utils.MessageCode.SKILL_CAST_END;

public class SkillCastedListener implements Telegraph {
    public static final float SOME_REASONABLE_TIME_TO_LET_ANIMATION_FINISH = 0.6f;
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<HealthComponent> hm = Mappers.health;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

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
                affectSurroundingEntities((SkillCastEndEvent) msg.extraInfo);
        }

        return true;
    }

    private void affectSurroundingEntities(SkillCastEndEvent e) {
        ImmutableArray<Entity> victims = engine.getEntitiesFor(Family.all(
                HealthComponent.class,
                PositionComponent.class).get());

        Entity caster = e.getCaster();

        AreaOfEffectCheckStrategy aoeCheck = buildAreaOfEffectChecker(
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

        freeCaster(caster);
    }

    private AreaOfEffectCheckStrategy buildAreaOfEffectChecker(AreaOfEffect aoe, int aoeSize, Vector2 pos){
        AreaOfEffectCheckStrategy aoeCheck;

        if(AreaOfEffect.CIRCLE.equals(aoe)){
            aoeCheck = new CircleAreaOfEffectCheckStrategyStrategyImpl(pos, aoeSize);
        }else{
            aoeCheck = new SquareAreaOfEffectCheckStrategyStrategyImpl(pos, aoeSize);
        }

        return aoeCheck;
    }

    private void applyEffectsToVictim(Entity victim, Entity caster, int damage) {
        HealthComponent healthComponent = hm.get(victim);
        healthComponent.currentHealth -= damage;

        EntityAttackedEvent event = new EntityAttackedEvent(victim, caster, damage);
        messageDispatcher.dispatchMessage(ENTITY_ATTACKED, event);
    }

    /* TODO: do this thing better. check isAnimationFinished in AnimableSpriteSystem maybe? */
    private void freeCaster(final Entity caster) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                StatusComponent statusComponent = sm.get(caster);
                statusComponent.setAction(Action.STANDING);
            }
        }, SOME_REASONABLE_TIME_TO_LET_ANIMATION_FINISH);
    }
}
