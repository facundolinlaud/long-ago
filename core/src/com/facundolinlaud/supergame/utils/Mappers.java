package com.facundolinlaud.supergame.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.components.skills.MeleeSkillComponent;
import com.facundolinlaud.supergame.components.skills.RangedSkillComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastRequestComponent;
import com.facundolinlaud.supergame.components.skills.SpellSkillComponent;
import com.facundolinlaud.supergame.components.sprite.AnimableSpriteComponent;
import com.facundolinlaud.supergame.components.sprite.StackableSpriteComponent;
import com.facundolinlaud.supergame.components.sprite.StackedSpritesComponent;
import com.facundolinlaud.supergame.components.StatusComponent;

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
    public static final ComponentMapper<SkillCastRequestComponent> skillCastRequest = ComponentMapper.getFor(SkillCastRequestComponent.class);
    public static final ComponentMapper<MeleeSkillComponent> meleeSkill = ComponentMapper.getFor(MeleeSkillComponent.class);
    public static final ComponentMapper<RangedSkillComponent> rangedSkill = ComponentMapper.getFor(RangedSkillComponent.class);
    public static final ComponentMapper<SpellSkillComponent> spellSkill = ComponentMapper.getFor(SpellSkillComponent.class);
}
