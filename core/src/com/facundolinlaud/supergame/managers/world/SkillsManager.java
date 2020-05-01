package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.factory.SkillsFactory2;
import com.facundolinlaud.supergame.services.AgentsService;
import com.facundolinlaud.supergame.services.CombatService;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.skills.Skill;
import com.facundolinlaud.supergame.skills.SkillBlackboard;

import java.util.HashSet;
import java.util.Set;

public class SkillsManager extends PoolableTaskManager {
    private Set<Entity> casters;
    private SkillsFactory2 factory;
    private LightsManager lightsManager;
    private CameraManager cameraManager;
    private AgentsService agentsService;
    private CombatService combatService;
    private ParticlesService particlesService;

    public SkillsManager(SkillsFactory2 factory, LightsManager lightsManager, CameraManager cameraManager,
                         AgentsService agentsService, CombatService combatService, ParticlesService particlesService) {
        this.factory = factory;
        this.casters = new HashSet();
        this.lightsManager = lightsManager;
        this.cameraManager = cameraManager;
        this.agentsService = agentsService;
        this.combatService = combatService;
        this.particlesService = particlesService;
    }

    public void requestCasting(Entity caster, String skillName) {
        if (casters.contains(caster)) {
            return;
        }

        System.out.println("## START CASTING ##");
        this.casters.add(caster);
        cast(caster, skillName);
    }

    private void cast(Entity caster, String skillName) {
        SkillBlackboard skillBlackboard = new SkillBlackboard(this, lightsManager, cameraManager,
                agentsService, combatService, particlesService, caster);

        Skill skill = factory.buildSkill(skillName);
        skill.setBlackboard(skillBlackboard);
        skill.activate();
    }

    public void endCasting(Entity caster) {
        System.out.println("## END CASTING ##");
        this.casters.remove(caster);
    }
}
