package com.facundolinlaud.supergame.utils.strategy.impl;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.model.skill.AreaOfEffect;
import com.facundolinlaud.supergame.model.skill.MeleeSkill;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.SkillCastEndEvent;
import com.facundolinlaud.supergame.utils.events.SkillCastInitializedEvent;
import com.facundolinlaud.supergame.utils.strategy.SkillCastStrategy;

import static com.facundolinlaud.supergame.utils.MessageCode.SKILL_CAST_END;
import static com.facundolinlaud.supergame.utils.MessageCode.SKILL_CAST_INITIALIZED;

public class MeleeSkillCastStrategy implements SkillCastStrategy<MeleeSkill> {
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private MessageDispatcher messageDispatcher;
    private AvailableSkillsFactory availableSkillsFactory;

    public MeleeSkillCastStrategy(AvailableSkillsFactory availableSkillsFactory) {
        this.messageDispatcher = MessageManager.getInstance();
        this.availableSkillsFactory = availableSkillsFactory;
    }

    @Override
    public void handleSkill(Entity caster, int skillId) {
        MeleeSkill meleeSkill = availableSkillsFactory.getMeleeSkill(skillId);
        castMeleeSkill(caster, meleeSkill);
    }

    private void castMeleeSkill(Entity caster, MeleeSkill skill) {
        // casting
        String skillName = skill.getName();
        float castTime = skill.getCastTime();
        SkillCastInitializedEvent castInitializedEvent = new SkillCastInitializedEvent(caster, castTime, skillName);
        messageDispatcher.dispatchMessage(SKILL_CAST_INITIALIZED, castInitializedEvent);

        // casting action
        Action skillAction = skill.getCastingAction();
        StatusComponent casterStatus = sm.get(caster);
        casterStatus.action = skillAction;

        // timer for execution
        Timer.schedule(new MeleeSkillCastExecution(caster, skill), castTime);
    }

    private class MeleeSkillCastExecution extends Timer.Task {
        private Entity caster;
        private MeleeSkill skill;

        public MeleeSkillCastExecution(Entity caster, MeleeSkill skill) {
            this.caster = caster;
            this.skill = skill;
        }

        @Override
        public void run() {
            // executing action
            Action executingAction = skill.getExecutingAction();
            StatusComponent casterStatus = sm.get(caster);
            casterStatus.action = executingAction;

            // skill casted event
            PositionComponent casterPosition = pm.get(caster);
            Vector2 skillEffectEpicenter = getSkillEffectEpicenterFromCasterPosition(
                    casterStatus.direction,
                    casterPosition.x,
                    casterPosition.y);
            AreaOfEffect aoe = skill.getAreaOfEffect();
            int aoeSize = skill.getAreaOfEffectSize();
            int damage = skill.getBaseDamage();
            SkillCastEndEvent event = new SkillCastEndEvent(caster, aoe, aoeSize, skillEffectEpicenter, damage);
            messageDispatcher.dispatchMessage(SKILL_CAST_END, event);

            // cooldown
            float cooldown = skill.getCoolDown();
        }

        private Vector2 getSkillEffectEpicenterFromCasterPosition(Direction direction, float x, float y){
            int xOffset = 0;
            int yOffset = 0;

            switch (direction) {
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

            return new Vector2(x + xOffset, y + yOffset);
        }
    }
}
