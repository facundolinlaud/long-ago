package com.facundolinlaud.supergame.skills;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Blackboard;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.managers.world.SkillsManager;
import com.facundolinlaud.supergame.services.AgentsService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;

public class SkillBlackboard extends Blackboard {
    private Entity caster;
    private SkillsManager skillsManager;
    private LightsManager lightsManager;
    private CameraManager cameraManager;
    private ParticlesService particlesService;
    private AgentsService agentsService;
    private CombatService combatService;

    public SkillBlackboard(SkillsManager skillsManager, LightsManager lightsManager, CameraManager cameraManager,
                           AgentsService agentsService, CombatService combatService, ParticlesService particlesService,
                           Entity caster) {
        this.skillsManager = skillsManager;
        this.lightsManager = lightsManager;
        this.cameraManager = cameraManager;
        this.particlesService = particlesService;
        this.agentsService = agentsService;
        this.combatService = combatService;
        this.caster = caster;
    }

    public Entity getCaster() {
        return caster;
    }

    public LightsManager getLightsManager() {
        return lightsManager;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }

    public AgentsService getAgentsService() {
        return agentsService;
    }

    public CombatService getCombatService() {
        return combatService;
    }

    public ParticlesService getParticlesService() {
        return particlesService;
    }

    @Override
    public PoolableTaskManager getDomainManager() {
        return skillsManager;
    }

    /**
     * Stack delegations
     *
     * @return
     */
    public Entity popEntity() {
        return getStack().pop().getEntityValue();
    }

    public Float popFloat() {
        return getStack().pop().getFloatValue();
    }

    public String popString() {
        return getStack().pop().getStringValue();
    }

    public void push(Value value) {
        getStack().push(value);
    }
}
