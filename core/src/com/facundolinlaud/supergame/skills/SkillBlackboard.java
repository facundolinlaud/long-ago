package com.facundolinlaud.supergame.skills;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.Blackboard;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.managers.world.SkillsManager;
import com.facundolinlaud.supergame.services.AgentsService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;

public class SkillBlackboard extends Blackboard {
    private Entity caster;
    private SkillsManager skillsManager;
    private LightsManager lightsManager;
    private CameraManager cameraManager;
    private AgentsService agentsService;
    private CombatService combatService;
    private ParticlesService particlesService;
    private ProjectilesService projectilesService;

    public SkillBlackboard(Entity caster, SkillsManager skillsManager, LightsManager lightsManager,
                           CameraManager cameraManager, AgentsService agentsService, CombatService combatService,
                           ParticlesService particlesService, ProjectilesService projectilesService) {
        this.caster = caster;
        this.skillsManager = skillsManager;
        this.lightsManager = lightsManager;
        this.cameraManager = cameraManager;
        this.agentsService = agentsService;
        this.combatService = combatService;
        this.particlesService = particlesService;
        this.projectilesService = projectilesService;
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

    @Override
    public SkillsManager getDomainManager() {
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
