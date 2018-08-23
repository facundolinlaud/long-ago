package com.facundolinlaud.supergame.ui.controller.impl;

import com.facundolinlaud.supergame.ui.controller.SkillCastingUIController;
import com.facundolinlaud.supergame.ui.view.SkillCastingUI;

/*
 * Eventualmente, si agrego interrupciones de casteos, voy a tener que agregar un onComponentRemoveListener escuchando
 * sobre SkillCastingComponent y llamar a este controller para ocultar la barra de casteo.
 */

public class SkillCastingUIControllerImpl implements SkillCastingUIController {

    private SkillCastingUI skillCastingUI;

    public SkillCastingUIControllerImpl(SkillCastingUI skillCastingUI) {
        this.skillCastingUI = skillCastingUI;
    }

    @Override
    public void updateCastingBar(String skillName, float timeToCast, float castTime) {
        float castingBarValue = timeToCast * 100 / castTime;
        skillCastingUI.update(skillName, castingBarValue);
    }
}
