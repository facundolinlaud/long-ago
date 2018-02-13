package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Timer;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.components.sprite.AnimableSpriteComponent;
import com.facundolinlaud.supergame.managers.world.PlayerInputObserver;
import com.facundolinlaud.supergame.model.Action;
import com.facundolinlaud.supergame.model.Direction;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.model.EquipSlot;
import com.facundolinlaud.supergame.model.EquipType;
import com.facundolinlaud.supergame.ui.model.equipment.Equipable;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/20/16.
 */
public class PlayerInputSystem extends EntitySystem {
    private ComponentMapper<WearComponent> wm = Mappers.wear;
    private ComponentMapper<EquipableComponent> em = Mappers.equipable;
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<AnimableSpriteComponent> asm = Mappers.animableSprite;

    private ImmutableArray<Entity> entities;
    private boolean waitingForActionEnd = false;
    private PlayerInputObserver playerInputObserver;

    public PlayerInputSystem(PlayerInputObserver playerInputObserver) {
        this.playerInputObserver = playerInputObserver;
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(StatusComponent.class, KeyboardComponent.class).get());
    }

    public void update(float deltaTime) {
        if(waitingForActionEnd)
            return;

        boolean isAttackingRequested = playerInputObserver.isAttackingRequested();

        for(Entity player : entities){
            StatusComponent status = sm.get(player);
            Direction newDirection = playerInputObserver.getPlayersNewDirection();

            if(newDirection != null && !isAttackingRequested){
                status.action = Action.WALKING;
                status.direction = newDirection;
            }else{
                if(isAttackingRequested){
                    if(status.direction != newDirection)
                        setSpriteIndexBackToZero(player);

                    status.action = resolveAttackingAnimation(player);
                    waitingForActionEnd = true;

                    scheduleStatusReset();
                }else{
                    status.action = Action.STANDING;
                }
            }

            status.gathering = playerInputObserver.isGatheringRequeste();
        }
    }

    private void scheduleStatusReset() {
        float delay = 0.75f; // seconds

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                waitingForActionEnd = false;
            }
        }, delay);
    }

    private void setSpriteIndexBackToZero(Entity entity){
        AnimableSpriteComponent animableSprite = asm.get(entity);
        animableSprite.resetStateTime();
    }

    private Action resolveAttackingAnimation(Entity player){
        Entity playerWeapon = wm.get(player).wearables.get(EquipSlot.WEAPON);

        if(playerWeapon == null)
            return Action.SWINGING;

        EquipableComponent weaponComponent = em.get(playerWeapon);
        EquipType equipType = weaponComponent.equipType;

        switch(equipType){
            case BOW:
                return Action.SHOOTING;
            case SPEAR:
                return Action.DASHING;
            case SWORD:
            case DAGGER:
                return Action.SWINGING;
            default:
                return null;
        }
    }
}