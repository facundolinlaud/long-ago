package com.facundolinlaud.supergame.strategies.skills.casted;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.SkillTargetedComponent;
import com.facundolinlaud.supergame.model.skill.AreaOfEffect;
import com.facundolinlaud.supergame.model.skill.MeleeSkill;
import com.facundolinlaud.supergame.model.skill.SkillType;
import com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck.AreaOfEffectCheckStrategy;
import com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck.CircleAreaOfEffectCheckStrategyStrategyImpl;
import com.facundolinlaud.supergame.strategies.skills.areaofeffectcheck.SquareAreaOfEffectCheckStrategyStrategyImpl;
import com.facundolinlaud.supergame.utils.Mappers;

public class MeleeSkillCastedStrategy implements SkillCastedStrategy<MeleeSkill> {
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private Engine engine;

    public MeleeSkillCastedStrategy(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(Entity caster, MeleeSkill meleeSkill) {
        Vector2 epicenter = calculateEpicenter(caster);
        affectSurroundingEntities(caster, meleeSkill, epicenter);
    }

    private Vector2 calculateEpicenter(Entity caster){
        PositionComponent casterPosition = pm.get(caster);
        StatusComponent casterStatus = sm.get(caster);

        int xOffset = 0;
        int yOffset = 0;

        switch (casterStatus.getDirection()) {
            case DOWN:
                yOffset = -1;
                break;
            case UP:
                yOffset = 1;
                break;
            case RIGHT:
                xOffset = 1;
                break;
            case LEFT:
                xOffset = -1;
                break;
        }

        return new Vector2(casterPosition.x + xOffset, casterPosition.y + yOffset);
    }

    private void affectSurroundingEntities(Entity caster, MeleeSkill meleeSkill, Vector2 epicenter) {
        ImmutableArray<Entity> victims = engine.getEntitiesFor(Family.all(
                HealthComponent.class,
                PositionComponent.class).get());

        AreaOfEffectCheckStrategy aoeCheck = buildAreaOfEffectChecker(
                meleeSkill.getAreaOfEffect(),
                meleeSkill.getAreaOfEffectSize(),
                epicenter);

        for(Entity victim : victims){
            if(victim != caster){
                PositionComponent victimPosition = pm.get(victim);

                if(aoeCheck.isInArea(victimPosition.x, victimPosition.y)){
                    applyEffectsToVictim(caster, victim, meleeSkill);
                }
            }
        }
    }

    // TODO: there's a builtin libgdx feature for this
    private AreaOfEffectCheckStrategy buildAreaOfEffectChecker(AreaOfEffect aoe, int aoeSize, Vector2 pos){
        switch(aoe){
            case CIRCLE:
                return new CircleAreaOfEffectCheckStrategyStrategyImpl(pos, aoeSize);
            default:
                return new SquareAreaOfEffectCheckStrategyStrategyImpl(pos, aoeSize);
        }
    }

    private void applyEffectsToVictim(Entity caster, Entity victim, MeleeSkill meleeSkill) {
        victim.add(new SkillTargetedComponent(caster, meleeSkill, SkillType.MELEE_SKILL));
    }
}
