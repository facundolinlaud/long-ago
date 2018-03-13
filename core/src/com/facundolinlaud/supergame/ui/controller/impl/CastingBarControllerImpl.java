package com.facundolinlaud.supergame.ui.controller.impl;

import com.facundolinlaud.supergame.ui.controller.CastingBarController;
import com.facundolinlaud.supergame.ui.view.CastingBarUI;
import com.facundolinlaud.supergame.utils.events.SkillCastInitializedEvent;

// TODO: ver si esto es necesario
public class CastingBarControllerImpl implements CastingBarController {

    private CastingBarUI castingBarUI;

    public CastingBarControllerImpl(CastingBarUI castingBarUI) {
        this.castingBarUI = castingBarUI;
    }

    @Override
    public void createCastingBar(SkillCastInitializedEvent event) {
        castingBarUI.start(event.getSkillName(), event.getCastTime());
    }

}
