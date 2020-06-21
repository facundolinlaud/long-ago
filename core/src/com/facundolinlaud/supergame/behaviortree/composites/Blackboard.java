package com.facundolinlaud.supergame.behaviortree.composites;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.managers.world.SkillsManager;
import com.facundolinlaud.supergame.managers.world.UIManager;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;

public abstract class Blackboard {
    private Entity agent;
    private LightsManager lightsManager;
    private CameraManager cameraManager;
    private SkillsManager skillsManager;
    private UIManager uiManager;
    private AgentService agentService;
    private CombatService combatService;
    private ParticlesService particlesService;
    private ProjectilesService projectilesService;

    public Blackboard(Entity agent, LightsManager lightsManager, CameraManager cameraManager, SkillsManager skillsManager,
                      UIManager uiManager, AgentService agentService, CombatService combatService,
                      ParticlesService particlesService, ProjectilesService projectilesService) {
        this.agent = agent;
        this.lightsManager = lightsManager;
        this.cameraManager = cameraManager;
        this.skillsManager = skillsManager;
        this.uiManager = uiManager;
        this.agentService = agentService;
        this.combatService = combatService;
        this.particlesService = particlesService;
        this.projectilesService = projectilesService;
    }

    public Entity getAgent() {
        return agent;
    }

    public LightsManager getLightsManager() {
        return lightsManager;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public AgentService getAgentService() {
        return agentService;
    }

    public CombatService getCombatService() {
        return combatService;
    }

    public ParticlesService getParticlesService() {
        return particlesService;
    }

    public ProjectilesService getProjectilesService() {
        return projectilesService;
    }

    public SkillsManager getSkillsManager() {
        return skillsManager;
    }

    public abstract PoolableTaskManager getDomainTaskManager();
}
