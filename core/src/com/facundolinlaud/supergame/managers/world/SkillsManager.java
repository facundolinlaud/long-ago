package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.factory.SkillsFactory2;
import com.facundolinlaud.supergame.services.AgentsService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.services.ProjectilesService;
import com.facundolinlaud.supergame.skills.Skill;
import com.facundolinlaud.supergame.skills.SkillBlackboard;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SkillsManager extends PoolableTaskManager {
    private Set<Entity> casters;
    private SkillsFactory2 factory;
    private LightsManager lightsManager;
    private CameraManager cameraManager;
    private AgentsService agentsService;
    private CombatService combatService;
    private ParticlesService particlesService;
    private ProjectilesService projectilesService;

    public SkillsManager(SkillsFactory2 factory, LightsManager lightsManager, CameraManager cameraManager,
                         AgentsService agentsService, CombatService combatService, ParticlesService particlesService,
                         ProjectilesService projectilesService) {
        this.factory = factory;
        this.casters = new HashSet();
        this.lightsManager = lightsManager;
        this.cameraManager = cameraManager;
        this.agentsService = agentsService;
        this.combatService = combatService;
        this.particlesService = particlesService;
        this.projectilesService = projectilesService;
    }

    public void requestCasting(Entity caster, String skillName) {
        if (casters.contains(caster)) {
            return;
        }

        this.casters.add(caster);
        cast(caster, skillName);
    }

    private void cast(Entity caster, String skillName) {
        SkillBlackboard skillBlackboard = new SkillBlackboard(caster, this, lightsManager, cameraManager,
                agentsService, combatService, particlesService, projectilesService);

        Skill skill = factory.buildSkill(skillName);
        skill.setBlackboard(skillBlackboard);
        skill.setStack(new Stack());
        skill.activate();
    }

    public void endCasting(Entity caster) {
        this.casters.remove(caster);
    }
}
