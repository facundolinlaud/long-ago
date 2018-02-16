package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.listeners.SkillCastedListener;
import com.facundolinlaud.supergame.listeners.SkillCastingListener;

public class GameSystemsManager {
    private AvailableSkillsFactory availableSkillsFactory;
    private SkillCastingListener skillCastingListener;
    private SkillCastedListener skillCastedListener;

    public GameSystemsManager(Engine engine) {
        initializeSkillsSystem(engine);
    }

    private void initializeSkillsSystem(Engine engine) {
        this.availableSkillsFactory = new AvailableSkillsFactory();
        this.skillCastingListener = new SkillCastingListener(engine, availableSkillsFactory);
        this.skillCastedListener = new SkillCastedListener(engine);
    }
}
