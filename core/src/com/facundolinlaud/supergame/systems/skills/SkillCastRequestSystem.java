package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.MeleeSkillComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastRequestComponent;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.events.SkillCastRequestedEvent;

import static com.facundolinlaud.supergame.utils.MessageCode.SKILL_CAST_REQUESTED;

public class SkillCastRequestSystem extends IteratingSystem {
    private ComponentMapper<SkillCastRequestComponent> scrcm = Mappers.skillCastRequest;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private MessageDispatcher messageDispatcher;

    public SkillCastRequestSystem() {
        super(Family.all(SkillCastRequestComponent.class, MeleeSkillComponent.class).get());
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        StatusComponent status = sm.get(caster);

        if(status.action.isBusy())
            return;

        dispatchSkill(caster);
        caster.remove(SkillCastRequestComponent.class);
    }

    private void dispatchSkill(Entity caster) {
        SkillCastRequestComponent requestComponent = scrcm.get(caster);
        int requestedSkillId = requestComponent.skillId;

        SkillCastRequestedEvent event = new SkillCastRequestedEvent(caster, requestedSkillId);
        messageDispatcher.dispatchMessage(SKILL_CAST_REQUESTED, event);
    }


}
