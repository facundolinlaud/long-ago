package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.managers.world.ParticleManager;

public class Factories {
    private AvailableSkillsFactory availableSkillsFactory;
    private ParticleFactory particleFactory;
    private ItemFactory itemFactory;
    private AgentFactory agentFactory;

    public Factories() {
        this.availableSkillsFactory = new AvailableSkillsFactory();
        this.particleFactory = new ParticleFactory(new ParticleManager());
        this.itemFactory = new ItemFactory();
        this.agentFactory = new AgentFactory(itemFactory, particleFactory);
    }

    public AvailableSkillsFactory getAvailableSkillsFactory() {
        return availableSkillsFactory;
    }

    public ParticleFactory getParticleFactory() {
        return particleFactory;
    }

    public ItemFactory getItemFactory() {
        return itemFactory;
    }

    public AgentFactory getAgentFactory() {
        return agentFactory;
    }
}
