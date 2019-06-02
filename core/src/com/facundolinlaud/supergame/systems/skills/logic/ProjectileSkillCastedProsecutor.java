package com.facundolinlaud.supergame.systems.skills.logic;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.skills.ProjectileComponent;
import com.facundolinlaud.supergame.components.skills.SkillTargetedComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.utils.Mappers;

public class ProjectileSkillCastedProsecutor extends BaseSkillCastedProsecutor {
    private ComponentMapper<ProjectileComponent> prm = Mappers.projectile;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    public ProjectileSkillCastedProsecutor(Engine engine, ParticleFactory particleFactory, LightsManager lightsManager) {
        super(engine, lightsManager, particleFactory);
    }

    public void hit(Entity victim, Entity projectile) {
        ProjectileComponent projectileComponent = prm.get(projectile);
        PositionComponent projectilePosition = pm.get(projectile);

        Vector2 position = projectilePosition.getPosition();
        Entity caster = projectileComponent.getCaster();
        Skill skill = projectileComponent.getSkill();

        if(victim == caster)
            return;

        applyEffects(victim, caster, skill);
        createParticleEffect(skill, position);
        createLightEffect(skill, position);
//        destroyProjectile(projectile);
    }

    private void applyEffects(Entity victim, Entity caster, Skill skill) {
        victim.add(new SkillTargetedComponent(caster, skill));
    }
}
