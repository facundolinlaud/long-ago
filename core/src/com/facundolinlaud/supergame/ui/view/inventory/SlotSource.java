package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    public SlotSource(Slot slot, Skin skin) {
        super(slot);

        this.skin = skin;
        this.slot = slot;
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Item item = slot.getItem();
        Payload payload = new Payload();
        payload.setDragActor(new Image(item.getPicture()));
        payload.setObject(item);

        return payload;
    }
}
