package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.TargetComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.factory.ModelFactory;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.managers.world.PlayerInputManager;
import com.facundolinlaud.supergame.managers.world.SkillsManager;
import com.facundolinlaud.supergame.model.skill.Skill;
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
    private ComponentMapper<TargetComponent> tm = Mappers.target;
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<BagComponent> bm = Mappers.bag;

    private SkillsManager skillsManager;
    private PlayerInputManager playerInputManager;
    private MessageDispatcher messageDispatcher;
    private ObservableMap<Integer, Skill> buttonsToSkills;

    public PlayerInputSystem(PlayerInputManager playerInputManager, SkillsManager skillsManager,
                             SkillsFactory skillsFactory, OverlayUIController overlayUIController) {
        super(Family.all(StatusComponent.class, KeyboardComponent.class, TargetComponent.class).get());
        this.messageDispatcher = MessageManager.getInstance();
        this.playerInputManager = playerInputManager;
        this.skillsManager = skillsManager;
        this.buttonsToSkills = new ObservableMapWrapper(new HashMap());

        addListeners(overlayUIController);
        loadButtonsToSkills(skillsFactory);
    }

    private void addListeners(OverlayUIController overlayUIController) {
        this.buttonsToSkills.addListener((MapChangeListener<Integer, Skill>)
                change -> overlayUIController.updateSkillBar(buttonsToSkills));
        this.messageDispatcher.addListener(this, SKILL_EQUIPPED);
        this.messageDispatcher.addListener(this, SKILL_DROPPED);
    }

    private void loadButtonsToSkills(SkillsFactory skillsFactory) {
        Map<Integer, String> skillBar = ModelFactory.getSkillBar();

        for (Map.Entry<Integer, String> entry : skillBar.entrySet()) {
            Skill skill = skillsFactory.get(entry.getValue());
            buttonsToSkills.put(entry.getKey(), skill);
        }
    }

    @Override
    protected void processEntity(Entity player, float deltaTime) {
        updateTarget(player);

        StatusComponent status = sm.get(player);
        BagComponent bag = bm.get(player);

        if (status.getAction().isBusy())
            return;

        boolean isSkillCastingRequested = playerInputManager.isPressingSkillButton();
        Direction newDirection = playerInputManager.getPlayersNewDirection();

        if (newDirection != null && !isSkillCastingRequested) {
            handleMovementCase(status, newDirection);
        } else {
            if (isSkillCastingRequested) {
                handleSkillCastingCase(player);
            } else {
                status.setAction(Action.STANDING);
            }
        }

        bag.setGathering(playerInputManager.isGatheringRequested());
    }

    private void updateTarget(Entity player) {
        PositionComponent positionComponent = pm.get(player);
        Vector2 playerPosition = positionComponent.getPosition();
        Vector2 cursorPosition = playerInputManager.getCursorPositionInMetersRelativeToScreenCenter();
        Vector2 clickedPosition = new Vector2(playerPosition.x + cursorPosition.x, playerPosition.y - cursorPosition.y);

        TargetComponent targetComponent = tm.get(player);
        targetComponent.setPosition(clickedPosition);
        targetComponent.setClicking(playerInputManager.isClicking());
    }

    private void handleMovementCase(StatusComponent status, Direction newDirection) {
        status.setAction(Action.WALKING);
        status.setDirection(newDirection);
    }

    private void handleSkillCastingCase(Entity caster) {
        Integer pressedSkillButton = playerInputManager.getPressedSkillButton();

        if (!skillForButtonExists(pressedSkillButton))
            return;

        Skill pressedSkill = buttonsToSkills.get(pressedSkillButton);
        skillsManager.requestCasting(caster, pressedSkill);
    }

    private boolean skillForButtonExists(int pressedSkillButton) {
        return buttonsToSkills.containsKey(pressedSkillButton);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        switch (msg.message) {
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