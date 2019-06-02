package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.ParticleComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.skills.ProjectileComponent;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.systems.skills.logic.ProjectileSkillCastedProsecutor;
import com.facundolinlaud.supergame.utils.Mappers;

public class ProjectileSystem extends IteratingSystem implements ContactListener {
    private static final Vector2 PROJECTILE_GRAVITY = new Vector2(0f, 0.015f);

    private ComponentMapper<ProjectileComponent> prm = Mappers.projectile;
    private ComponentMapper<ParticleComponent> pam = Mappers.particle;
    private ComponentMapper<PositionComponent> pom = Mappers.position;
    private ComponentMapper<BodyComponent> bom = Mappers.body;

    private ProjectileSkillCastedProsecutor prosecutor;

    public ProjectileSystem(Engine engine, ParticleFactory particleFactory, LightsManager lightsManager, World world){
        super(Family.all(ProjectileComponent.class, PositionComponent.class, BodyComponent.class).get());
        this.prosecutor = new ProjectileSkillCastedProsecutor(engine, particleFactory, lightsManager);
        world.setContactListener(this);
    }

    @Override
    protected void processEntity(Entity projectile, float deltaTime) {
        applyGravityToProjectile(projectile);
        destroyProjectileIfTravelledTooFar(projectile);
    }

    private void destroyProjectileIfTravelledTooFar(Entity projectile){
        ProjectileComponent projectileComponent = prm.get(projectile);
        PositionComponent positionComponent = pom.get(projectile);

        float distanceTravelled = projectileComponent.getOrigin().dst(positionComponent.getPosition());
        Skill skill = projectileComponent.getSkill();

        if(distanceTravelled >= skill.getProjectileInformation().getMaxTravelDistance())
            destroyProjectile(projectile);
    }

    private void applyGravityToProjectile(Entity projectile) {
        BodyComponent bodyComponent = bom.get(projectile);
        bodyComponent.body.applyForceToCenter(PROJECTILE_GRAVITY, true);
    }

    // TODO: refactor this ugly thing
    // this should go elsewhere
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
            this.prosecutor.hit(victim, projectile);

        destroyProjectile(projectile);
    }

    private boolean isVictimValid(Entity victim) {
        return victim != null;
    }

    private void destroyProjectile(Entity projectile){
        if(pam.has(projectile)){
            ParticleComponent particleComponent = pam.get(projectile);
            particleComponent.setEntityJustParticle(true);

            projectile.remove(ProjectileComponent.class);
            projectile.remove(BodyComponent.class);
            projectile.remove(RenderComponent.class);
        }else{
            getEngine().removeEntity(projectile);
        }
    }

    @Override
    public void endContact(Contact contact) {}

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
}
