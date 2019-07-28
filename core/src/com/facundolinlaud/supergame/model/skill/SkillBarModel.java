package com.facundolinlaud.supergame.model.skill;

import java.util.Map;

public class SkillBarModel {
    private Map<Integer, Integer> buttonsToSkillsIds;

    public Map<Integer, Integer> getButtonsToSkillsIds() {
        return buttonsToSkillsIds;
    }

    public void setButtonsToSkillsIds(Map<Integer, Integer> buttonsToSkillsIds) {
        this.buttonsToSkillsIds = buttonsToSkillsIds;
    }
}
