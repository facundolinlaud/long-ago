package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.managers.world.PlayerInputObserver;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillType;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/20/16.
 */
public class PlayerInputSystem extends IteratingSystem {
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<BagComponent> bm = Mappers.bag;

    private PlayerInputObserver playerInputObserver;
    private SkillsFactory skillsFactory;

    public PlayerInputSystem(PlayerInputObserver playerInputObserver, SkillsFactory skillsFactory) {
        super(Family.all(StatusComponent.class, KeyboardComponent.class).get());
        this.playerInputObserver = playerInputObserver;
        this.skillsFactory = skillsFactory;
    }

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        StatusComponent status = sm.get(player);
        BagComponent bag = bm.get(player);

        if(status.getAction().isBusy())
            return;

        boolean isSkillCastingRequested = playerInputObserver.isAttackingRequested();
        Direction newDirection = playerInputObserver.getPlayersNewDirection();

        if(newDirection != null && !isSkillCastingRequested){
            handleMovementCase(status, newDirection);
        }else{
            if(isSkillCastingRequested){
                handleSkillCastingCase(player, status);
            }else{
                status.setAction(Action.STANDING);
            }
        }

        bag.gathering = playerInputObserver.isGatheringRequested();
    }

    private void handleMovementCase(StatusComponent status, Direction newDirection) {
        status.setAction(Action.WALKING);
        status.setDirection(newDirection);
    }

    private void handleSkillCastingCase(Entity caster, StatusComponent status) {
        Integer requestedSkillId = playerInputObserver.getPlayersSkillId();
        Skill requestedSkill = skillsFactory.get(requestedSkillId);
        SkillType skillType = requestedSkill.getSkillType();

        if(skillType == SkillType.SPELL || skillType == SkillType.PROJECTILE)
            caster.add(new SkillClickComponent());

        caster.add(new SkillCastingRequestComponent(requestedSkill));
    }
}