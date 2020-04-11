package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.skills.SkillBlackboard;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.SkillCastedEvent;

import static com.facundolinlaud.supergame.utils.events.Messages.SKILL_CASTING_ENDED;

public class CastTask extends Task<SkillBlackboard> implements Telegraph {
    private static ComponentMapper<SkillCastingComponent> scm = Mappers.skillCasting;
    private MessageDispatcher messageDispatcher = MessageManager.getInstance();

    private Skill skill;

    public CastTask(Skill skill) {
        this.skill = skill;
    }

    @Override
    public void activate() {
        Entity caster = getBlackboard().getCaster();
        caster.add(new SkillCastingComponent(skill));

        messageDispatcher.addListener(this, SKILL_CASTING_ENDED);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        Entity caster = getBlackboard().getCaster();
        SkillCastedEvent event = (SkillCastedEvent) msg.extraInfo;

        if(isThisSkill(caster, event)){
            completed();
        }

        return false;
    }

    private boolean isThisSkill(Entity caster, SkillCastedEvent event) {
        return event.getSkill().equals(skill) && event.getCaster().equals(caster);
    }
}
