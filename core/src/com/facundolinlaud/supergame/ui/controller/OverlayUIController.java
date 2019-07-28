package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.ui.view.OverlayUI;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.Messages;
import com.facundolinlaud.supergame.utils.events.SkillCastedEvent;

/**
 * Created by facundo on 3/29/16.
 */
public class OverlayUIController implements Telegraph {
    private ComponentMapper<KeyboardComponent> km = Mappers.keyboard;

    private OverlayUI overlayUI;

    public OverlayUIController(OverlayUI overlayUI) {
        this.overlayUI = overlayUI;
    }

    public void setHealth(float health) {
        overlayUI.setHealth(health);
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
        switch(msg.message){
            case Messages.SKILL_CASTED:
                onSkillCasted((SkillCastedEvent) msg.extraInfo);
                break;
        }

        return true;
    }

    private void onSkillCasted(SkillCastedEvent event){
        Entity caster = event.getCaster();

        if(isMainPlayer(caster))
            overlayUI.beginCooldown(event.getSkill());
    }

    private boolean isMainPlayer(Entity entity){
        return km.has(entity);
    }

    public void updateCastingBar(String skillName, float timeToCast, float castTime) {
        float castingBarValue = timeToCast * 100 / castTime;
        this.overlayUI.updateCastingBar(skillName, castingBarValue);
    }
}
