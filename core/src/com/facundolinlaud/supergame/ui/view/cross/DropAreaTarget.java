package com.facundolinlaud.supergame.ui.view.cross;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.utils.events.ItemFromEquipmentDropped;
import com.facundolinlaud.supergame.utils.events.ItemFromInventoryDropped;
import com.facundolinlaud.supergame.utils.mediator.Mediator;
import com.facundolinlaud.supergame.utils.mediator.Messenger;

/**
 * Created by facundo on 3/30/16.
 */
public class DropAreaTarget extends Target implements Messenger {
    private Mediator uiMediator;

    public DropAreaTarget(Actor actor, Mediator uiMediator) {
        super(actor);

        this.uiMediator = uiMediator;
    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        SlotSource slotSource = (SlotSource) source;
        Item item = (Item) payload.getObject();

        switch(slotSource.getSlotType()){
            case INVENTORY_SLOT:
                uiMediator.raise(this, ItemFromInventoryDropped.class, new ItemFromInventoryDropped(item));
                break;
            case EQUIPMENT_SLOT:
                uiMediator.raise(this, ItemFromEquipmentDropped.class, new ItemFromEquipmentDropped(item));
                break;
        }
    }
}
