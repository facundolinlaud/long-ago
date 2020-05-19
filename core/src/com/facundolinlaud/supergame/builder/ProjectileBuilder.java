package com.facundolinlaud.supergame.builder;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.ParticleComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.skills.ProjectileComponent;
import com.facundolinlaud.supergame.factory.PhysicsFactory;
import com.facundolinlaud.supergame.factory.TextureFactory;
import com.facundolinlaud.supergame.model.RenderPriority;

public class ProjectileBuilder {
    public static final float ORIGIN_OFFSET = 0.6f;
    private Entity entity;
    private Vector2 origin;
    private Vector2 direction;

    public ProjectileBuilder(Entity caster, float maxTravelDistance, Vector2 origin) {
        this.entity = new Entity().add(new ProjectileComponent(caster, maxTravelDistance, origin));
        this.origin = origin;
    }

    public ProjectileBuilder withPicture(String imageName) {
        float rotation = calculateRotation(direction);

        Sprite sprite = TextureFactory.getSprite(imageName);
        sprite.setRotation(rotation);

        this.entity.add(new RenderComponent(sprite, RenderPriority.PARTICLE));

        return this;
    }

    public ProjectileBuilder to(Vector2 destination, float shootingForce) {
        direction = resolveDirection(origin, destination);

        PositionComponent positionComponent = new PositionComponent(
                origin.x + ORIGIN_OFFSET * direction.x,
                origin.y + ORIGIN_OFFSET * direction.y);

        Body body = PhysicsFactory.get().createProjectileBody();
        body.applyForceToCenter(
                shootingForce * direction.x,
                shootingForce * direction.y,
                true);
        BodyComponent bodyComponent = new BodyComponent(body, this.entity);

        this.entity
                .add(bodyComponent)
                .add(positionComponent);

        return this;
    }

    public ProjectileBuilder withParticles(ParticleEffectPool.PooledEffect effect) {
        this.entity.add(new ParticleComponent(effect, false));
        return this;
    }

    public Entity build() {
        return this.entity;
    }

    private float calculateRotation(Vector2 direction) {
        return direction.angle();
    }

    private Vector2 resolveDirection(Vector2 origin, Vector2 destination) {
        Vector2 direction = new Vector2(destination.x - origin.x, destination.y - origin.y);
        return direction.scl(1 / direction.len());
    }
}
