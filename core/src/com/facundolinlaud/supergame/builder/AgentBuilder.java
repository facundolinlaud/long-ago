package com.facundolinlaud.supergame.builder;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.components.sprite.AnimableSpriteComponent;
import com.facundolinlaud.supergame.components.sprite.RefreshSpriteRequirementComponent;
import com.facundolinlaud.supergame.components.sprite.StackedSpritesComponent;
import com.facundolinlaud.supergame.factory.ModelFactory;
import com.facundolinlaud.supergame.factory.PhysicsFactory;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.strategies.renderposition.SpriteRenderPositionStrategyImpl;

import java.util.Map;

public class AgentBuilder {
    private Entity entity;

    public AgentBuilder(float velocity) {
        this.entity = new Entity()
            .add(new RenderComponent(new SpriteRenderPositionStrategyImpl()))
            .add(new BodyComponent(PhysicsFactory.get().createItemBody()))
            .add(new StatusComponent())
            .add(new AnimableSpriteComponent())
            .add(new StackedSpritesComponent(ModelFactory.getDefaultAnimationModel()))
            .add(new RefreshSpriteRequirementComponent())
            .add(new VelocityComponent(velocity));
    }

    public AgentBuilder withAI(float viewDistance){
        this.entity.add(new AIComponent(viewDistance));
        return this;
    }

    public AgentBuilder withHealth(float total, float current){
        this.entity.add(new HealthComponent(total, current));
        return this;
    }

    public AgentBuilder withEquipment(Map<EquipSlot, Entity> equipment){
        this.entity.add(new WearComponent(equipment));
        return this;
    }

    public AgentBuilder at(float x, float y){
        this.entity.add(new PositionComponent(x, y));
        return this;
    }

    public Entity build(){
        return this.entity;
    }

}
