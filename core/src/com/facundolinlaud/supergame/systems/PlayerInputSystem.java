package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.utils.Timer;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.components.sprite.AnimableSpriteComponent;
import com.facundolinlaud.supergame.managers.world.PlayerInputObserver;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.SkillCastRequestedEvent;

import static com.facundolinlaud.supergame.utils.MessageCode.SKILL_CAST_REQUESTED;

/**
 * Created by facundo on 3/20/16.
 */
public class PlayerInputSystem extends IteratingSystem {
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<BagComponent> bm = Mappers.bag;
    private ComponentMapper<AnimableSpriteComponent> asm = Mappers.animableSprite;

    private PlayerInputObserver playerInputObserver;
    private MessageDispatcher messageDispatcher;

    public PlayerInputSystem(PlayerInputObserver playerInputObserver) {
        super(Family.all(StatusComponent.class, KeyboardComponent.class).get());

        this.playerInputObserver = playerInputObserver;
        this.messageDispatcher = MessageManager.getInstance();
    }

    // el skill se esta requesteando infinitamente!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // el skill se esta requesteando infinitamente!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // el skill se esta requesteando infinitamente!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        StatusComponent status = sm.get(player);
        BagComponent bag = bm.get(player);

        if(status.action.isBusy())
            return;

        boolean isSkillCastingRequested = playerInputObserver.isAttackingRequested();
        Direction newDirection = playerInputObserver.getPlayersNewDirection();

        if(newDirection != null && !isSkillCastingRequested){
            handleMovementCase(status, newDirection);
        }else{
            if(isSkillCastingRequested){
                if(status.direction != newDirection)
                    setSpriteIndexBackToZero(player);

                handleSkillCastingCase(player, status);
                scheduleStatusReset(status);
            }else{
                status.action = Action.STANDING;
            }
        }

        bag.gathering = playerInputObserver.isGatheringRequested();
    }

    private void handleMovementCase(StatusComponent status, Direction newDirection) {
        status.action = Action.WALKING;
        status.direction = newDirection;
    }

    private void handleSkillCastingCase(Entity caster, StatusComponent status) {
        Integer requestedSkillId = playerInputObserver.getPlayersSkillId();
        SkillCastRequestedEvent event = new SkillCastRequestedEvent(caster, requestedSkillId);

        messageDispatcher.dispatchMessage(SKILL_CAST_REQUESTED, event);
        System.out.println("mando mensaje con skill " + requestedSkillId);
    }

    private void setSpriteIndexBackToZero(Entity entity){
        AnimableSpriteComponent animableSprite = asm.get(entity);
        animableSprite.resetStateTime();
    }

    private void scheduleStatusReset(final StatusComponent status) {
        float delay = 0.60f; // seconds

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                status.action = Action.STANDING;
            }
        }, delay);
    }
}