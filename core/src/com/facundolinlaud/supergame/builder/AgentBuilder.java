package com.facundolinlaud.supergame.builder;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.components.sprite.AnimableSpriteComponent;
import com.facundolinlaud.supergame.components.sprite.RefreshSpriteRequirementComponent;
import com.facundolinlaud.supergame.components.sprite.StackedSpritesComponent;
import com.facundolinlaud.supergame.factory.ModelFactory;
import com.facundolinlaud.supergame.factory.PhysicsFactory;
import com.facundolinlaud.supergame.model.RenderPriority;
import com.facundolinlaud.supergame.model.agent.Attributes;
import com.facundolinlaud.supergame.model.agent.NPCInformation;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.renderposition.SpriteRenderPositionStrategyImpl;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;

import java.util.List;
import java.util.Map;

public class AgentBuilder {
    public static final int HEALTH_PER_STAMINA_POINT = 10;
    private static final int MANA_PER_INTELLIGENCE_POINT = 10;

    private Entity entity;

    public AgentBuilder(float velocity) {
        this.entity = new Entity();

        this.entity.add(new RenderComponent(new SpriteRenderPositionStrategyImpl(), RenderPriority.AGENT))
            .add(new BodyComponent(PhysicsFactory.get().createItemBody(), this.entity))
            .add(new StatusComponent())
            .add(new AnimableSpriteComponent())
            .add(new StackedSpritesComponent(ModelFactory.getDefaultAnimationModel()))
            .add(new RefreshSpriteRequirementComponent())
            .add(new VelocityComponent(velocity));
    }

    public AgentBuilder withAI(NPCInformation npcInformation){
        this.entity.add(new AIComponent(npcInformation));
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

    public AgentBuilder withEquipment(Map<EquipSlot, Entity> equipment,
                                      MapChangeListener<? super EquipSlot, ? super Entity> listener){
        this.entity.add(new WearComponent(equipment, listener));
        return this;
    }

    public AgentBuilder withAttributes(Attributes attr){
        float health = attr.getStamina() * HEALTH_PER_STAMINA_POINT;
        float mana = attr.getIntelligence() * MANA_PER_INTELLIGENCE_POINT;

        this.entity.add(new AttributesComponent(
                attr.getAgility(),
                attr.getStrength(),
                attr.getIntelligence(),
                attr.getStamina()));

        return withHealth(health, health).withMana(mana);
    }

    public AgentBuilder withBag(List<Entity> bag){
        this.entity.add(new BagComponent(bag));
        return this;
    }

    public AgentBuilder withBag(List<Entity> bag, ListChangeListener<? super Entity> listener){
        this.entity.add(new BagComponent(bag, listener));
        return this;
    }

    public AgentBuilder withKeyboardControl(){
        this.entity.add(new KeyboardComponent());
        return this;
    }

    public AgentBuilder at(float x, float y){
        this.entity.add(new PositionComponent(x, y));
        return this;
    }

    public AgentBuilder withParticles(ParticleEffectPool.PooledEffect pooledEffect){
        this.entity.add(new ParticleComponent(pooledEffect, false));
        return this;
    }

    public AgentBuilder withSkills(List<Skill> skills){
        this.entity.add(new SkillsComponent(skills));
        return this;
    }

    public AgentBuilder withMana(float totalMana){
        this.entity.add(new ManaComponent(totalMana));
        return this;
    }

    public Entity build(){
        return this.entity;
    }

}
