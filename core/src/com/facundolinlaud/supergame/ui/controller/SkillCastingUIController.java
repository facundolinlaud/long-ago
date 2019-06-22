package com.facundolinlaud.supergame.ui.controller;

import com.facundolinlaud.supergame.ui.view.OverlayUI;
import com.facundolinlaud.supergame.ui.view.SkillCastingUI;

/*
 * Eventualmente, si agrego interrupciones de casteos, voy a tener que agregar un onComponentRemoveListener escuchando
 * sobre SkillCastingComponent y llamar a este controller para ocultar la barra de casteo.
 */

public class SkillCastingUIController {

    private SkillCastingUI skillCastingUI;

    public SkillCastingUIController(SkillCastingUI skillCastingUI, OverlayUI overlayUI) {
        this.skillCastingUI = skillCastingUI;
    }

    public void updateCastingBar(String skillName, float timeToCast, float castTime) {
        float castingBarValue = timeToCast * 100 / castTime;
        skillCastingUI.update(skillName, castingBarValue);
    }
}
