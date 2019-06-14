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
import com.facundolinlaud.supergame.model.skill.Skill;

public class ProjectileBuilder {
    public static final float SHOOTING_FORCE = 7f;
    public static final float ORIGIN_OFFSET = 0.6f;
    private Entity entity;

    public ProjectileBuilder(Entity caster, Skill skill, Vector2 origin) {
        this.entity = new Entity().add(new ProjectileComponent(caster, skill, origin));
    }

    public ProjectileBuilder withPicture(String imageName, float rotation){
        Sprite sprite = TextureFactory.getSprite(imageName);
        sprite.setRotation(rotation);

        this.entity.add(new RenderComponent(sprite, RenderPriority.PARTICLE));

        return this;
    }

    public ProjectileBuilder withOrigin(Vector2 origin, Vector2 direction){
        this.entity.add(new PositionComponent(origin.x + ORIGIN_OFFSET * direction.x,
                origin.y + ORIGIN_OFFSET * direction.y));

        return this;
    }

    public ProjectileBuilder withDirection(Vector2 direction){
        Body body = PhysicsFactory.get().createProjectileBody();
        body.applyForceToCenter(SHOOTING_FORCE * direction.x, SHOOTING_FORCE * direction.y, true);
        BodyComponent bodyComponent = new BodyComponent(body, this.entity);
        this.entity.add(bodyComponent);

        return this;
    }

    public ProjectileBuilder withParticles(ParticleEffectPool.PooledEffect effect){
        this.entity.add(new ParticleComponent(effect, false));
        return this;
    }

    public Entity build(){
        return this.entity;
    }
}
