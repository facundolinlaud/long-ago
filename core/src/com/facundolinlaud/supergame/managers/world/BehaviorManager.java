package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.facundolinlaud.supergame.ai.behavior.BehaviorBlackboard;
import com.facundolinlaud.supergame.ai.behavior.BehaviorTask;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderAuthority;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.components.ai.BehaviorComponent;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.Stack;

public class BehaviorManager extends PoolableTaskManager implements EntityListener {
    private ComponentMapper<BehaviorComponent> bm = Mappers.behavior;

    private SkillsManager skillsManager;
    private LightsManager lightsManager;
    private CameraManager cameraManager;
    private UIManager uiManager;
    private AgentService agentService;
    private CombatService combatService;
    private ParticlesService particlesService;
    private ProjectilesService projectilesService;
    private PathFinderAuthority pathFinderAuthority;

    public BehaviorManager(MapManager mapManager, PhysicsManager physicsManager, SkillsManager skillsManager,
                           LightsManager lightsManager, CameraManager cameraManager, UIManager uiManager,
                           AgentService agentService, CombatService combatService, ParticlesService particlesService,
                           ProjectilesService projectilesService) {
        this.pathFinderAuthority = new PathFinderAuthority(mapManager, physicsManager);
        this.skillsManager = skillsManager;
        this.lightsManager = lightsManager;
        this.cameraManager = cameraManager;
        this.uiManager = uiManager;
        this.agentService = agentService;
        this.combatService = combatService;
        this.particlesService = particlesService;
        this.projectilesService = projectilesService;
    }

    public PathFinderAuthority getPathFinderAuthority() {
        return pathFinderAuthority;
    }

    @Override
    public void entityAdded(Entity agent) {
        BehaviorBlackboard blackboard = new BehaviorBlackboard(agent, lightsManager, cameraManager, skillsManager,
                uiManager, agentService, combatService, particlesService, projectilesService, this);

        BehaviorComponent behaviorComponent = bm.get(agent);
        BehaviorTask behaviorTask = behaviorComponent.getBehaviorTask();
        behaviorTask.setBlackboard(blackboard);
        behaviorTask.setStack(new Stack());
        behaviorTask.activate();
    }

    @Override
    public void entityRemoved(Entity entity) {

    }
}