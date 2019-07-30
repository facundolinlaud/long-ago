package com.facundolinlaud.supergame.builder;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.ParticleComponent;
import com.facundolinlaud.supergame.components.PositionComponent;

public class ParticleBuilder {
    private Entity particle;

    public ParticleBuilder(ParticleEffectPool.PooledEffect effect) {
        this.particle = new Entity().add(new ParticleComponent(effect, true));
    }

    public ParticleBuilder(ParticleEffectPool.PooledEffect effect, Entity entity){
        this.particle = entity.add(new ParticleComponent(effect, false));
    }

    public ParticleBuilder at(Vector2 position){
        this.particle.add(new PositionComponent(position));
        return this;
    }

    public Entity build(){
        return this.particle;
    }
}
