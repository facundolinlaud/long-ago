package com.facundolinlaud.supergame.ui.view.overlay.skillsbar;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.SkillSlotSource;
import com.facundolinlaud.supergame.utils.events.Messages;
import com.facundolinlaud.supergame.utils.events.SkillEquippedEvent;

public class SkillBarSlotTarget extends DragAndDrop.Target {
    private MessageDispatcher messageDispatcher;
    private SkillBarSlot slot;

    public SkillBarSlotTarget(SkillBarSlot slot) {
        super(slot);
        this.slot = slot;
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        SkillSlotSource slotSource = (SkillSlotSource) source;
        Skill skill = (Skill) payload.getObject();
        int slotIndex = slot.getIndex();

        switch(slotSource.getSlotType()){
            case SKILL_TREE_SLOT:
                messageDispatcher.dispatchMessage(Messages.SKILL_EQUIPPED, new SkillEquippedEvent(skill, slotIndex));
                break;
        }
    }
}
