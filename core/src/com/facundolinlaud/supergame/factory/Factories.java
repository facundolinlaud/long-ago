package com.facundolinlaud.supergame.factory;

public class Factories {
    private AvailableSkillsFactory availableSkillsFactory;

    public Factories() {
        this.availableSkillsFactory = new AvailableSkillsFactory();
    }

    public AvailableSkillsFactory getAvailableSkillsFactory() {
        return availableSkillsFactory;
    }

}
