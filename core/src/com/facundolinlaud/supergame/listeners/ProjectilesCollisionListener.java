package com.facundolinlaud.supergame.listeners;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.managers.world.ScreenShakeManager;
import com.facundolinlaud.supergame.strategies.skills.projectile.ProjectileDestructionStrategy;
import com.facundolinlaud.supergame.systems.skills.logic.ProjectileSkillCastedProsecutor;

public class ProjectilesCollisionListener implements ContactListener {
    private ProjectileDestructionStrategy destructionStrategy;
    private ProjectileSkillCastedProsecutor prosecutor;

    public ProjectilesCollisionListener(Engine engine, ParticleFactory particleFactory,
                                        LightsManager lightsManager, CameraManager cameraManager){
        this.prosecutor = new ProjectileSkillCastedProsecutor(engine, particleFactory, lightsManager, cameraManager);
        this.destructionStrategy = new ProjectileDestructionStrategy(engine);
    }

    @Override
    public void beginContact(Contact contact) {
        Entity projectile;
        Entity victim = null;

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if(a.isBullet()){
            projectile = (Entity) a.getUserData();

            if(b.getUserData() != null)
                victim = (Entity) b.getUserData();
        } else if(b.isBullet()){
            projectile = (Entity) b.getUserData();

            if(a.getUserData() != null)
                victim = (Entity) a.getUserData();
        } else return;

        if(isVictimValid(victim))
            prosecutor.hit(victim, projectile);

        destructionStrategy.destroy(projectile);
    }

    private boolean isVictimValid(Entity victim) {
        return victim != null;
    }

    @Override
    public void endContact(Contact contact) { }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) { }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) { }
}
