package com.facundolinlaud.supergame.ui.view.cross.draganddrop;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.factory.TextureFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.cross.SkillSlot;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.SlotType;

public class SkillSlotSource extends DragAndDrop.Source {
    private SkillSlot slot;
    private SlotType slotType;
    private int index;

    public SkillSlotSource(SkillSlot slot, SlotType slotType) {
        super(slot);
        this.slot = slot;
        this.slotType = slotType;
    }

    public SkillSlotSource(SkillSlot slot, SlotType slotType, int index) {
        super(slot);
        this.slot = slot;
        this.slotType = slotType;
        this.index = index;
    }

    @Override
    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Skill skill = slot.getContent();

        DragAndDrop.Payload payload = new DragAndDrop.Payload();
        TextureRegion tr = TextureFactory.getRegion(skill.getPicturePath());
        payload.setDragActor(new Image(tr));
        payload.setObject(new SkillDragInformation(index, skill));

        return payload;
    }

    public SlotType getSlotType() {
        return slotType;
    }
}