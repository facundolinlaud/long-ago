package com.facundolinlaud.supergame.ui.view.cross.draganddrop;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.Slot;

/**
 * Created by facundo on 3/30/16.
 */
public class ItemSlotSource extends Source {
    private Slot<Item> slot;
    private SlotType slotType;

    public ItemSlotSource(Slot slot, SlotType slotType) {
        super(slot);

        this.slot = slot;
        this.slotType = slotType;
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Item item = slot.getContent();
        Payload payload = new Payload();
        payload.setDragActor(new Image(item.getPicture()));
        payload.setObject(item);

        return payload;
    }

    public SlotType getSlotType(){
        return this.slotType;
    }
}
