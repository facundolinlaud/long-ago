package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.factory.ModelFactory;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.managers.world.PlayerInputManager;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillType;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.Messages;
import com.facundolinlaud.supergame.utils.events.SkillBarChangedEvent;

import java.util.List;

/**
 * Created by facundo on 3/20/16.
 */
public class PlayerInputSystem extends IteratingSystem implements Telegraph {
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<BagComponent> bm = Mappers.bag;

    private PlayerInputManager playerInputObserver;
    private MessageDispatcher messageDispatcher;
    private List<Skill> buttonsToSkills;


    public PlayerInputSystem(PlayerInputManager playerInputObserver, SkillsFactory skillsFactory) {
        super(Family.all(StatusComponent.class, KeyboardComponent.class).get());

        this.buttonsToSkills = skillsFactory.get(ModelFactory.getSkillBar());
        this.messageDispatcher = MessageManager.getInstance();
        this.playerInputObserver = playerInputObserver;

        this.messageDispatcher.addListener(this, Messages.SKILL_BAR_CHANGED);
        this.messageDispatcher.dispatchMessage(this, Messages.SKILL_BAR_CHANGED,
                new SkillBarChangedEvent(buttonsToSkills));
    }

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        StatusComponent status = sm.get(player);
        BagComponent bag = bm.get(player);

        if(status.getAction().isBusy())
            return;

        boolean isSkillCastingRequested = playerInputObserver.isPressingSkillButton();
        Direction newDirection = playerInputObserver.getPlayersNewDirection();

        if(newDirection != null && !isSkillCastingRequested){
            handleMovementCase(status, newDirection);
        }else{
            if(isSkillCastingRequested){
                handleSkillCastingCase(player);
            }else{
                status.setAction(Action.STANDING);
            }
        }

        bag.setGathering(playerInputObserver.isGatheringRequested());
    }

    private void handleMovementCase(StatusComponent status, Direction newDirection) {
        status.setAction(Action.WALKING);
        status.setDirection(newDirection);
    }

    private void handleSkillCastingCase(Entity caster) {
        Integer pressedSkillButton = playerInputObserver.getPressedSkillButton();


        if(!existsSkillForButton(pressedSkillButton))
            return;

        Skill requestedSkill = buttonsToSkills.get(pressedSkillButton);
        SkillType skillType = requestedSkill.getSkillType();

        if(skillType == SkillType.SPELL || skillType == SkillType.PROJECTILE)
            caster.add(new SkillClickComponent());

        caster.add(new SkillCastingRequestComponent(requestedSkill));
    }

    private boolean existsSkillForButton(int pressedSkillButton){
        return buttonsToSkills.size() > pressedSkillButton && buttonsToSkills.get(pressedSkillButton) != null;
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        switch(msg.message){
            case Messages.SKILL_BAR_CHANGED:
                handleSkillBarChanged((SkillBarChangedEvent) msg.extraInfo);
        }

        return false;
    }

    private void handleSkillBarChanged(SkillBarChangedEvent event) {
        this.buttonsToSkills = event.getSkills();
    }
}