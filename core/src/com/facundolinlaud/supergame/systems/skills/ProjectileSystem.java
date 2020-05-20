package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.skills.ProjectileComponent;
import com.facundolinlaud.supergame.strategies.skills.projectile.ProjectileDestructionStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

public class ProjectileSystem extends IteratingSystem {
    private static final Vector2 PROJECTILE_GRAVITY = new Vector2(0f, -0.03f);

    private ComponentMapper<ProjectileComponent> prm = Mappers.projectile;
    private ComponentMapper<PositionComponent> pom = Mappers.position;
    private ComponentMapper<BodyComponent> bom = Mappers.body;

    private ProjectileDestructionStrategy destructionStrategy;

    public ProjectileSystem(Engine engine) {
        super(Family.all(ProjectileComponent.class, PositionComponent.class, BodyComponent.class).get());
        this.destructionStrategy = new ProjectileDestructionStrategy(engine);
    }

    @Override
    protected void processEntity(Entity projectile, float deltaTime) {
        applyGravityToProjectile(projectile);
        destroyProjectileIfTravelledTooFar(projectile);
    }

    private void destroyProjectileIfTravelledTooFar(Entity projectile) {
        ProjectileComponent projectileComponent = prm.get(projectile);
        PositionComponent positionComponent = pom.get(projectile);

        Vector2 currentPosition = positionComponent.getPosition();
        projectileComponent.travel(currentPosition);

        if (projectileComponent.hasTravelDistanceMaxedOut()) {
            destructionStrategy.destroy(projectile);
            projectileComponent.getOnMiss().run();
        }
    }

    private void applyGravityToProjectile(Entity projectile) {
        BodyComponent bodyComponent = bom.get(projectile);
        bodyComponent.body.applyForceToCenter(PROJECTILE_GRAVITY, true);
    }
}
