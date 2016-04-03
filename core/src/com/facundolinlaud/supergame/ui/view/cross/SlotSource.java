package com.facundolinlaud.supergame.ui.view.cross;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.facundolinlaud.supergame.ui.model.Item;

/**
 * Created by facundo on 3/30/16.
 */
public class SlotSource extends Source {

    private Skin skin;
    private Slot slot;
    private SlotType slotType;

    public SlotSource(Slot slot, Skin skin, SlotType slotType) {
        super(slot);

        this.skin = skin;
        this.slot = slot;
        this.slotType = slotType;
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Item item = slot.getItem();
        Payload payload = new Payload();
        payload.setDragActor(new Image(item.getPicture()));
        payload.setObject(item);

        return payload;
    }

    public SlotType getSlotType(){
        return this.slotType;
    }
}
