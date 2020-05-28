package com.facundolinlaud.supergame.skills;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Blackboard;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.managers.world.SkillsManager;
import com.facundolinlaud.supergame.managers.world.UIManager;
import com.facundolinlaud.supergame.services.AgentsService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;
import com.facundolinlaud.supergame.ui.controller.OverlayUIController;

public class SkillBlackboard extends Blackboard {
    private Entity caster;
    private SkillsManager skillsManager;
    private LightsManager lightsManager;
    private CameraManager cameraManager;
    private AgentsService agentsService;
    private CombatService combatService;
    private ParticlesService particlesService;
    private ProjectilesService projectilesService;
    private OverlayUIController overlayUIController;

    public SkillBlackboard(Entity caster, SkillsManager skillsManager, LightsManager lightsManager,
                           CameraManager cameraManager, UIManager uiManager, AgentsService agentsService,
                           CombatService combatService, ParticlesService particlesService,
                           ProjectilesService projectilesService) {
        this.caster = caster;
        this.skillsManager = skillsManager;
        this.lightsManager = lightsManager;
        this.cameraManager = cameraManager;
        this.agentsService = agentsService;
        this.combatService = combatService;
        this.particlesService = particlesService;
        this.projectilesService = projectilesService;
        this.overlayUIController = uiManager.getOverlayUIController();
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

    public ProjectilesService getProjectilesService() {
        return projectilesService;
    }

    public ParticlesService getParticlesService() {
        return particlesService;
    }

    public OverlayUIController getOverlayUIController() {
        return overlayUIController;
    }

    @Override
    public SkillsManager getDomainManager() {
        return skillsManager;
    }

}
