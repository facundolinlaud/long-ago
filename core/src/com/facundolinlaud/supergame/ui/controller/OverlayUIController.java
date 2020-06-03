package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.ui.view.OverlayUI;
import com.facundolinlaud.supergame.utils.events.SkillCooldownStartEvent;

import java.util.Map;

import static com.facundolinlaud.supergame.utils.events.Messages.*;

/**
 * Created by facundo on 3/29/16.
 */
public class OverlayUIController implements Telegraph {
    private OverlayUI overlayUI;
    private AgentService agentService;

    public OverlayUIController(OverlayUI overlayUI, AgentService agentService) {
        this.overlayUI = overlayUI;
        this.agentService = agentService;
    }

    public void setHealth(float health, float total) {
        overlayUI.setHealth(health, total);
    }

    public void setMana(float mana, float total) {
        overlayUI.setMana(mana, total);
    }

    public void setFPS(int fps) {
        overlayUI.setFPS(fps);
    }

    public void setPosition(Vector2 position) {
        overlayUI.setPosition(position);
    }

    public void setBodyPosition(Vector2 position) {
        overlayUI.setBodyPosition(position);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        switch (msg.message) {
            case REJECTED_SKILL_DUE_TO_NO_MANA:
                overlayUI.popNoManaNotification();
                break;
            case REJECTED_SKILL_DUE_TO_NOT_READY:
                overlayUI.popSkillNotReadyNotification();
                break;
            case REJECTED_SKILL_DUE_TO_WEAPON:
                overlayUI.popNoAdequateWeaponNotification();
                break;
            case CUSTOM_MESSAGE:
                overlayUI.popNotification((String) msg.extraInfo);
                break;
            case SKILL_COOLDOWN_START:
                SkillCooldownStartEvent event = (SkillCooldownStartEvent) msg.extraInfo;
                onSkillCooldownStart(event);
                break;
        }

        return true;
    }

    public void updateCastingBar(String skillName, float progress) {
        this.overlayUI.updateCastingBar(skillName, progress);
    }

    public void updateSkillBar(Map<Integer, Skill> buttonsToSkills) {
        overlayUI.updateSkillBar(buttonsToSkills);
    }

    private void onSkillCooldownStart(SkillCooldownStartEvent event) {
        Entity caster = event.getCaster();

        if (agentService.isPlayer(caster)) {
            overlayUI.beginCooldown(event.getSkill());
        }
    }
}
