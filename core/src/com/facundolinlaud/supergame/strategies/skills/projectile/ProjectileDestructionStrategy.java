package com.facundolinlaud.supergame.strategies.skills.projectile;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.ParticleComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.skills.ProjectileComponent;
import com.facundolinlaud.supergame.utils.Mappers;

public class ProjectileDestructionStrategy {
    private ComponentMapper<ParticleComponent> pam = Mappers.particle;

    private Engine engine;

    public ProjectileDestructionStrategy(Engine engine) {
        this.engine = engine;
    }

    public void destroy(Entity projectile){
        if(pam.has(projectile)){
            ParticleComponent particleComponent = pam.get(projectile);
            particleComponent.setEntityJustParticle(true);

            projectile.remove(ProjectileComponent.class);
            projectile.remove(BodyComponent.class);
            projectile.remove(RenderComponent.class);
        }else{
            engine.removeEntity(projectile);
        }
    }
}
