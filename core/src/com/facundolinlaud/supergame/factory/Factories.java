package com.facundolinlaud.supergame.factory;

public class Factories {
    private AvailableSkillsFactory availableSkillsFactory;
    private ItemFactory itemFactory;
    private AgentFactory agentFactory;

    public Factories() {
        this.availableSkillsFactory = new AvailableSkillsFactory();
        this.itemFactory = new ItemFactory();
        this.agentFactory = new AgentFactory(itemFactory);
    }

    public AvailableSkillsFactory getAvailableSkillsFactory() {
        return availableSkillsFactory;
    }

    public ItemFactory getItemFactory() {
        return itemFactory;
    }

    public AgentFactory getAgentFactory() {
        return agentFactory;
    }
}
