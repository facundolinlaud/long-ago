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
import com.facundolinlaud.supergame.dto.agent.Attributes;
import com.facundolinlaud.supergame.dto.agent.AIInformation;
import com.facundolinlaud.supergame.model.ai.BehaviorType;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.sprite.RawAnimationModel;
import com.facundolinlaud.supergame.strategies.renderposition.SpriteRenderPositionStrategyImpl;

import java.util.List;
import java.util.Map;

public class AgentBuilder {
    public static final int HEALTH_PER_STAMINA_POINT = 10;
    private static final int MANA_PER_INTELLIGENCE_POINT = 10;

    private Entity entity;

    public AgentBuilder(int id) {
        this.entity = new Entity();

        this.entity
            .add(new StatusComponent())
            .add(new IdComponent(id));
    }

    public AgentBuilder withAI(BehaviorType behaviorType, float viewDistance){
        this.entity.add(new AIComponent(behaviorType, viewDistance));
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

    public AgentBuilder withEquipment(Map<EquipSlot, Entity> equipment, int onChangeMessage){
        this.entity.add(new WearComponent(equipment, onChangeMessage));
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

    public AgentBuilder withBag(List<Entity> bag, int gold){

        this.entity.add(new BagComponent(bag, gold));
        return this;
    }

    public AgentBuilder withBag(List<Entity> bag, int gold, int onChangeMessage){
        this.entity.add(new BagComponent(bag, gold, onChangeMessage));
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

    public AgentBuilder withSkills(List<Skill> skills, int assignablePoints){
        this.entity.add(new SkillsComponent(skills, assignablePoints));
        return this;
    }

    public AgentBuilder withSkills(List<Skill> skills, int assignablePoints, int onChangeMessage){
        this.entity.add(new SkillsComponent(skills, assignablePoints, onChangeMessage));
        return this;
    }

    public AgentBuilder withMana(float totalMana){
        this.entity.add(new ManaComponent(totalMana));
        return this;
    }

    public AgentBuilder talkable(){
        this.entity.add(new InteractionComponent());
        return this;
    }

    public AgentBuilder withAnimations(RawAnimationModel rawAnimationModel){
        this.entity.add(new RenderComponent(new SpriteRenderPositionStrategyImpl(), RenderPriority.AGENT))
                   .add(new AnimableSpriteComponent())
                   .add(new StackedSpritesComponent(rawAnimationModel))
                   .add(new RefreshSpriteRequirementComponent());

        return this;
    }

    public AgentBuilder withVelocity(float velocity){
        this.entity.add(new VelocityComponent(velocity));
        return this;
    }

    public AgentBuilder withBody(){
        this.entity.add(new BodyComponent(PhysicsFactory.get().createItemBody(), this.entity));
        return this;
    }

    public Entity build(){
        return this.entity;
    }

}
