package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.facundolinlaud.supergame.ai.behavior.BehaviorBlackboard;
import com.facundolinlaud.supergame.ai.behavior.BehaviorTask;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.components.ai.BehaviorComponent;
import com.facundolinlaud.supergame.components.ai.BehavioringComponent;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.HashMap;
import java.util.Map;

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
    private PathFindingManager pathFindingManager;

    private Map<Entity, BehaviorTask> behaviors;

    public BehaviorManager(SkillsManager skillsManager, LightsManager lightsManager, CameraManager cameraManager,
                           UIManager uiManager, AgentService agentService, CombatService combatService,
                           ParticlesService particlesService, ProjectilesService projectilesService,
                           PathFindingManager pathFindingManager) {
        this.skillsManager = skillsManager;
        this.lightsManager = lightsManager;
        this.cameraManager = cameraManager;
        this.uiManager = uiManager;
        this.agentService = agentService;
        this.combatService = combatService;
        this.particlesService = particlesService;
        this.projectilesService = projectilesService;
        this.pathFindingManager = pathFindingManager;
        this.behaviors = new HashMap();
    }

    @Override
    public void entityAdded(Entity agent) {
        BehaviorBlackboard blackboard = new BehaviorBlackboard(agent, lightsManager, cameraManager, skillsManager,
                uiManager, agentService, combatService, particlesService, projectilesService, this,
                pathFindingManager);

        BehaviorComponent behaviorComponent = bm.get(agent);
        BehaviorTask behaviorTask = behaviorComponent.getBehaviorTask();
        behaviorTask.setBlackboard(blackboard);
    }

    public void activate(Entity agent) {
        agent.add(new BehavioringComponent());

        BehaviorComponent behaviorComponent = bm.get(agent);
        BehaviorTask behaviorTask = behaviorComponent.getBehaviorTask();
        behaviors.put(agent, behaviorTask);
        behaviorTask.activate();
    }

    @Override
    public void entityRemoved(Entity agent) {
        behaviors.remove(agent).abort();
    }

    public void onBehaviorFinished(Entity agent) {
        agent.remove(BehavioringComponent.class);
    }
}