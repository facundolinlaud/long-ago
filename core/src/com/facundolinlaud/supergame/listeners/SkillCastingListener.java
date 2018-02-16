package com.facundolinlaud.supergame.listeners;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.model.skill.SkillType;
import com.facundolinlaud.supergame.utils.events.SkillCastRequestedEvent;
import com.facundolinlaud.supergame.utils.strategy.SkillCastStrategy;
import com.facundolinlaud.supergame.utils.strategy.impl.MeleeSkillCastStrategy;

import java.util.HashMap;
import java.util.Map;

import static com.facundolinlaud.supergame.utils.MessageCode.SKILL_CAST_REQUESTED;

public class SkillCastingListener implements Telegraph {
    private Engine engine;
    private MessageDispatcher messageDispatcher;
    private Map<SkillType, SkillCastStrategy> strategies;
    private AvailableSkillsFactory availableSkillsFactory;

    public SkillCastingListener(Engine engine, AvailableSkillsFactory availableSkillsFactory) {
        this.engine = engine;
        this.availableSkillsFactory = availableSkillsFactory;
        this.messageDispatcher = MessageManager.getInstance();
        this.messageDispatcher.addListener(this, SKILL_CAST_REQUESTED);

        this.strategies = new HashMap<>();
        this.strategies.put(SkillType.MELEE_SKILL, new MeleeSkillCastStrategy(availableSkillsFactory));
//        this.strategies.put(SkillType.RANGED_SKILL, new RangedSkillCastStrategy());
//        this.strategies.put(SkillType.SPELL_SKILL, new SpellSkillCastStrategy());
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        switch(msg.message) {
            case SKILL_CAST_REQUESTED:
                handleSkillCasting((SkillCastRequestedEvent) msg.extraInfo);
                break;
        }

        return true;
    }

    private void handleSkillCasting(SkillCastRequestedEvent e) {
        Entity caster = e.getCaster();
        int skillId = e.getSkillId();
        SkillType skillType = availableSkillsFactory.getSkillTypeById(skillId);

        strategies.get(skillType).handleSkill(caster, skillId);
    }
    // fijarme que con esto por ahi puedo hacer cosas locas
//    Timer.schedule(new Timer.Task(){
//        @Override
//        public void run() {
//            status.action = Action.STANDING;
//        }
//    }, delay);
}
