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
    private BodyComponent bodyComponent;

    public AgentBuilder(String id) {
        entity = new Entity()
            .add(new StatusComponent())
            .add(new IdComponent(id))
            .add(new TargetComponent());
    }

    public AgentBuilder withAI(BehaviorType behaviorType, float viewDistance){
        entity.add(new AIComponent(behaviorType, viewDistance));
        return this;
    }

    public AgentBuilder withHealth(float total, float current){
        entity.add(new HealthComponent(total, current));
        return this;
    }

    public AgentBuilder withEquipment(Map<EquipSlot, Entity> equipment){
        entity.add(new WearComponent(equipment));
        return this;
    }

    public AgentBuilder withEquipment(Map<EquipSlot, Entity> equipment, int onChangeMessage){
        entity.add(new WearComponent(equipment, onChangeMessage));
        return this;
    }

    public AgentBuilder withAttributes(Attributes attr){
        float health = attr.getStamina() * HEALTH_PER_STAMINA_POINT;
        float mana = attr.getIntelligence() * MANA_PER_INTELLIGENCE_POINT;

        entity.add(new AttributesComponent(
                attr.getAgility(),
                attr.getStrength(),
                attr.getIntelligence(),
                attr.getStamina()));

        return withHealth(health, health).withMana(mana);
    }

    public AgentBuilder withBag(List<Entity> bag, int gold){

        entity.add(new BagComponent(bag, gold));
        return this;
    }

    public AgentBuilder withBag(List<Entity> bag, int gold, int onChangeMessage){
        entity.add(new BagComponent(bag, gold, onChangeMessage));
        return this;
    }

    public AgentBuilder withKeyboardControl(){
        entity.add(new KeyboardComponent());
        return this;
    }

    public AgentBuilder at(float x, float y){
        entity.add(new PositionComponent(x, y));
        return this;
    }

    public AgentBuilder withParticles(ParticleEffectPool.PooledEffect pooledEffect){
        entity.add(new ParticleComponent(pooledEffect, false));
        return this;
    }

    public AgentBuilder withSkills(List<Skill> skills, int assignablePoints){
        entity.add(new SkillsComponent(skills, assignablePoints));
        return this;
    }

    public AgentBuilder withSkills(List<Skill> skills, int assignablePoints, int onChangeMessage){
        entity.add(new SkillsComponent(skills, assignablePoints, onChangeMessage));
        return this;
    }

    public AgentBuilder withMana(float totalMana){
        entity.add(new ManaComponent(totalMana));
        return this;
    }

    public AgentBuilder talkable(){
        entity.add(new InteractionComponent());
        return this;
    }

    public AgentBuilder withAnimations(RawAnimationModel rawAnimationModel){
        entity.add(new RenderComponent(new SpriteRenderPositionStrategyImpl(), RenderPriority.AGENT))
                   .add(new AnimableSpriteComponent())
                   .add(new StackedSpritesComponent(rawAnimationModel))
                   .add(new RefreshSpriteRequirementComponent());

        return this;
    }

    public AgentBuilder withVelocity(float velocity){
        entity.add(new VelocityComponent(velocity));
        return this;
    }

    public AgentBuilder withBody(){
        bodyComponent = new BodyComponent(PhysicsFactory.get().createItemBody());
        entity.add(bodyComponent);

        return this;
    }

    public void shootable(){
        bodyComponent.setShootable(entity);
    }

    public Entity build(){
        return entity;
    }

}
