package com.facundolinlaud.supergame.listeners;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.facundolinlaud.supergame.components.ProjectileComponent;
import com.facundolinlaud.supergame.services.ProjectilesService;
import com.facundolinlaud.supergame.utils.Mappers;

public class ProjectilesCollisionListener implements ContactListener {
    private ComponentMapper<ProjectileComponent> prm = Mappers.projectile;

    private ProjectilesService projectilesService;

    public ProjectilesCollisionListener(ProjectilesService projectilesService) {
        this.projectilesService = projectilesService;
    }

    @Override
    public void beginContact(Contact contact) {
        Entity projectile;
        Entity victim = null;

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if (a.isBullet()) {
            projectile = (Entity) a.getUserData();

            if (b.getUserData() != null)
                victim = (Entity) b.getUserData();
        } else if (b.isBullet()) {
            projectile = (Entity) b.getUserData();

            if (a.getUserData() != null)
                victim = (Entity) a.getUserData();
        } else {
            return;
        }

        if(!prm.has(projectile)) return;

        ProjectileComponent projectileComponent = prm.get(projectile);

        if (isVictimValid(victim)) {
            projectileComponent.getOnHit().accept(victim);
        } else {
            projectileComponent.getOnMiss().run();
        }

        projectilesService.destroy(projectile);
    }

    private boolean isVictimValid(Entity victim) {
        return victim != null;
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
