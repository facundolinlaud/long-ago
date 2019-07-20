package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Engine;
import com.facundolinlaud.supergame.managers.world.ParticleManager;

public class Factories {
    private SkillsFactory skillsFactory;
    private ParticleFactory particleFactory;
    private ItemFactory itemFactory;
    private AgentFactory agentFactory;

    public Factories(Engine engine) {
        this.skillsFactory = new SkillsFactory();
        this.particleFactory = new ParticleFactory(new ParticleManager());
        this.itemFactory = new ItemFactory();
        this.agentFactory = new AgentFactory(engine, this.itemFactory, this.particleFactory, this.skillsFactory);
    }

    public SkillsFactory getSkillsFactory() {
        return skillsFactory;
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
