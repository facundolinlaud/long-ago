package com.facundolinlaud.supergame.ui.controller.impl;

import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.ui.controller.CastingBarController;
import com.facundolinlaud.supergame.ui.view.CastingBarUI;
import com.facundolinlaud.supergame.utils.events.SkillCastInitializedEvent;

import static com.facundolinlaud.supergame.utils.MessageCode.SKILL_CAST_INITIALIZED;

public class CastingBarControllerImpl implements CastingBarController, Telegraph {

    private CastingBarUI castingBarUI;

    public CastingBarControllerImpl(CastingBarUI castingBarUI) {
        this.castingBarUI = castingBarUI;

        MessageManager.getInstance().addListener(this, SKILL_CAST_INITIALIZED);
    }

    @Override
    public void createCastingBar(SkillCastInitializedEvent event) {
        castingBarUI.start(event.getSkillName(), event.getCastTime());
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        switch(msg.message){
            case SKILL_CAST_INITIALIZED:
                createCastingBar((SkillCastInitializedEvent) msg.extraInfo);
                break;
        }

        return true;
    }
}
