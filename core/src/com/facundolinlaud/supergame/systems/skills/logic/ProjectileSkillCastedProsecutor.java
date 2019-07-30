package com.facundolinlaud.supergame.systems.skills.logic;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.skills.ProjectileComponent;
import com.facundolinlaud.supergame.components.skills.SkillTargetedComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.skills.projectile.ProjectileDestructionStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

public class ProjectileSkillCastedProsecutor extends BaseSkillCastedProsecutor {
    private ComponentMapper<ProjectileComponent> prm = Mappers.projectile;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private ProjectileDestructionStrategy destructionStrategy;

    public ProjectileSkillCastedProsecutor(Engine engine, ParticleFactory particleFactory,
                                           LightsManager lightsManager, CameraManager cameraManager) {
        super(engine, lightsManager, particleFactory, cameraManager);
        this.destructionStrategy = new ProjectileDestructionStrategy(engine);
    }

    public void hit(Entity victim, Entity projectile) {
        if(!prm.has(projectile))
            return;

        ProjectileComponent projectileComponent = prm.get(projectile);
        PositionComponent projectilePosition = pm.get(projectile);

        Vector2 position = projectilePosition.getPosition();
        Entity caster = projectileComponent.getCaster();
        Skill skill = projectileComponent.getSkill();

        if(victim == caster)
            return;

        applyEffects(victim, caster, skill);
        createAreaParticleEffect(skill, position);
        createHitParticleEffect(victim, skill);
        createLightEffect(skill, position);
        shakeScreen(skill, position);

        this.destructionStrategy.destroy(projectile);
    }

    private void applyEffects(Entity victim, Entity caster, Skill skill) {
        victim.add(new SkillTargetedComponent(caster, skill));
    }
}
