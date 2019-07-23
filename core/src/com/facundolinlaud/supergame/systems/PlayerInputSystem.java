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
import com.facundolinlaud.supergame.ui.controller.OverlayUIController;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.SkillEquippedEvent;
import com.sun.javafx.collections.ObservableMapWrapper;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Map;

import static com.facundolinlaud.supergame.utils.events.Messages.SKILL_DROPPED;
import static com.facundolinlaud.supergame.utils.events.Messages.SKILL_EQUIPPED;

/**
 * Created by facundo on 3/20/16.
 */
public class PlayerInputSystem extends IteratingSystem implements Telegraph {
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<BagComponent> bm = Mappers.bag;

    private PlayerInputManager playerInputObserver;
    private MessageDispatcher messageDispatcher;
    private ObservableMap<Integer, Skill> buttonsToSkills;

    public PlayerInputSystem(PlayerInputManager playerInputObserver, SkillsFactory skillsFactory, OverlayUIController
                             overlayUIController) {
        super(Family.all(StatusComponent.class, KeyboardComponent.class).get());
        this.messageDispatcher = MessageManager.getInstance();
        this.playerInputObserver = playerInputObserver;
        this.buttonsToSkills = new ObservableMapWrapper(new HashMap());

        addListeners(overlayUIController);
        loadButtonsToSkills(skillsFactory);
    }

    private void addListeners(OverlayUIController overlayUIController){
        this.buttonsToSkills.addListener((MapChangeListener<Integer, Skill>)
                change -> overlayUIController.updateSkillBar(buttonsToSkills));
        this.messageDispatcher.addListener(this, SKILL_EQUIPPED);
        this.messageDispatcher.addListener(this, SKILL_DROPPED);
    }

    private void loadButtonsToSkills(SkillsFactory skillsFactory){
        Map<Integer, Integer> skillBar = ModelFactory.getSkillBar();
        for(Map.Entry<Integer, Integer> entry : skillBar.entrySet()){
            Skill skill = skillsFactory.get(entry.getValue());
            buttonsToSkills.put(entry.getKey(), skill);
        }
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

        if(skillType.isTwoClick())
            caster.add(new SkillClickComponent());

        caster.add(new SkillCastingRequestComponent(requestedSkill));
    }

    private boolean existsSkillForButton(int pressedSkillButton){
        return buttonsToSkills.containsKey(pressedSkillButton);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        switch(msg.message){
            case SKILL_EQUIPPED:
                onSkillEquipped((SkillEquippedEvent) msg.extraInfo);
                break;
            case SKILL_DROPPED:
                onSkillDropped((Integer) msg.extraInfo);
                break;
        }

        return false;
    }

    private void onSkillEquipped(SkillEquippedEvent e) {
        this.buttonsToSkills.put(e.getIndex(), e.getSkill());
    }

    private void onSkillDropped(Integer index) {
        this.buttonsToSkills.remove(index);
    }
}