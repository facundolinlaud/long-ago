package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.utils.events.ItemsPositionSwapEvent;
import com.facundolinlaud.supergame.utils.mediator.Mediator;
import com.facundolinlaud.supergame.utils.mediator.Messenger;

/**
 * Created by facundo on 3/30/16.
 */
public class SlotTarget extends Target implements Messenger {

    private Slot slot;
    private Mediator uiMediator;

    public SlotTarget(Slot slot, Mediator uiMediator) {
        super(slot);

        this.slot = slot;
        this.uiMediator = uiMediator;
    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        Item a = slot.getItem();
        Item b = (Item) payload.getObject();

        uiMediator.raise(this, ItemsPositionSwapEvent.class, new ItemsPositionSwapEvent(a, b));
    }
}
