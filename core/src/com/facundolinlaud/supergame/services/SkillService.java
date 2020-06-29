package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.components.SkillsComponent;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.SkillCooldownStartEvent;

import static com.facundolinlaud.supergame.utils.events.Messages.SKILL_COOLDOWN_START;

public class SkillService {
    private ComponentMapper<SkillsComponent> sm = Mappers.skills;

    private MessageDispatcher messageDispatcher;

    public SkillService() {
        this.messageDispatcher = MessageManager.getInstance();
    }

    public void startCoolDown(Entity caster, Skill skill) {
        SkillsComponent skillsComponent = sm.get(caster);
        skillsComponent.startCoolDown(skill);

        SkillCooldownStartEvent event = new SkillCooldownStartEvent(caster, skill);
        messageDispatcher.dispatchMessage(SKILL_COOLDOWN_START, event);
    }

    public boolean canCast(Entity caster, Skill skill) {
        SkillsComponent skillsComponent = sm.get(caster);

        boolean isCoolingDown = skillsComponent.isCoolingDown(skill);
        boolean hasSkill = skillsComponent.has(skill);

        return !isCoolingDown && hasSkill;
    }
}
