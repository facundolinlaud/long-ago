package com.facundolinlaud.supergame.ui.view.cross.draganddrop;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.factory.TextureFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.cross.SkillSlot;

public class SkillSlotSource extends DragAndDrop.Source {
    private SkillSlot slot;
    private SlotType slotType;

    public SkillSlotSource(SkillSlot slot, SlotType slotType) {
        super(slot);
        this.slot = slot;
        this.slotType = slotType;
    }

    @Override
    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Skill skill = slot.getContent();
        DragAndDrop.Payload payload = new DragAndDrop.Payload();
        TextureRegion tr = TextureFactory.getRegion(skill.getPicturePath());
        payload.setDragActor(new Image(tr));
        payload.setObject(skill);

        return payload;
    }

    public SlotType getSlotType() {
        return slotType;
    }
}