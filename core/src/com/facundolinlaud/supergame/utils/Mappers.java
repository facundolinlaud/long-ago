package com.facundolinlaud.supergame.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.components.ai.AIMoveToComponent;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.components.spawn.SpawnLocationComponent;
import com.facundolinlaud.supergame.components.spawn.SpawnedAgentComponent;
import com.facundolinlaud.supergame.components.sprite.AnimableSpriteComponent;
import com.facundolinlaud.supergame.components.sprite.StackableSpriteComponent;
import com.facundolinlaud.supergame.components.sprite.StackedSpritesComponent;

/**
 * Created by facundo on 3/17/16.
 */
public class Mappers {
    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<RenderComponent> render = ComponentMapper.getFor(RenderComponent.class);
    public static final ComponentMapper<StatusComponent> status = ComponentMapper.getFor(StatusComponent.class);
    public static final ComponentMapper<KeyboardComponent> keyboard = ComponentMapper.getFor(KeyboardComponent.class);
    public static final ComponentMapper<BodyComponent> body = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<HealthComponent> health = ComponentMapper.getFor(HealthComponent.class);
    public static final ComponentMapper<ItemComponent> item = ComponentMapper.getFor(ItemComponent.class);
    public static final ComponentMapper<BagComponent> bag = ComponentMapper.getFor(BagComponent.class);
    public static final ComponentMapper<AttributesComponent> attributes = ComponentMapper.getFor(AttributesComponent.class);
    public static final ComponentMapper<WearComponent> wear = ComponentMapper.getFor(WearComponent.class);
    public static final ComponentMapper<EquipableComponent> equipable = ComponentMapper.getFor(EquipableComponent.class);
    public static final ComponentMapper<StackableSpriteComponent> stackableSprite = ComponentMapper.getFor(StackableSpriteComponent.class);
    public static final ComponentMapper<StackedSpritesComponent> stackedSprites = ComponentMapper.getFor(StackedSpritesComponent.class);
    public static final ComponentMapper<AnimableSpriteComponent> animableSprite = ComponentMapper.getFor(AnimableSpriteComponent.class);
    public static final ComponentMapper<ParticleComponent> particle = ComponentMapper.getFor(ParticleComponent.class);
    public static final ComponentMapper<AIComponent> ai = ComponentMapper.getFor(AIComponent.class);
    public static final ComponentMapper<AIMoveToComponent> aiMoveTo = ComponentMapper.getFor(AIMoveToComponent.class);
    public static final ComponentMapper<SpawnLocationComponent> spawnLocation = ComponentMapper.getFor(SpawnLocationComponent.class);
    public static final ComponentMapper<SpawnedAgentComponent> spawnedAgent = ComponentMapper.getFor(SpawnedAgentComponent.class);
    public static final ComponentMapper<ProjectileComponent> projectile = ComponentMapper.getFor(ProjectileComponent.class);
    public static final ComponentMapper<SkillsComponent> skills = ComponentMapper.getFor(SkillsComponent.class);
    public static final ComponentMapper<ManaComponent> mana = ComponentMapper.getFor(ManaComponent.class);
    public static final ComponentMapper<IdComponent> id = ComponentMapper.getFor(IdComponent.class);
    public static final ComponentMapper<InteractionComponent> interaction = ComponentMapper.getFor(InteractionComponent.class);
    public static final ComponentMapper<TargetComponent> target = ComponentMapper.getFor(TargetComponent.class);
}
