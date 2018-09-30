package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.Slot;
import com.facundolinlaud.supergame.ui.view.cross.SlotSource;
import com.facundolinlaud.supergame.utils.events.ItemsPositionSwapEvent;
import com.facundolinlaud.supergame.utils.events.UnequipItemEvent;
import com.facundolinlaud.supergame.utils.mediator.Mediator;
import com.facundolinlaud.supergame.utils.mediator.Messenger;

/**
 * Created by facundo on 3/30/16.
 */
public class InventorySlotTarget extends Target implements Messenger {

    private Slot<Item> slot;
    private Mediator uiMediator;

    public InventorySlotTarget(InventorySlot slot, Mediator uiMediator) {
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
        SlotSource slotSource = (SlotSource) source;

        switch(slotSource.getSlotType()){
            case INVENTORY_SLOT:
                handleInventorySourceDrop(payload);
                break;
            case EQUIPMENT_SLOT:
                handleEquipmentSourceDrop(payload);
                break;
        }
    }

    private void handleInventorySourceDrop(Payload payload) {
        Item a = slot.getContent();
        Item b = (Item) payload.getObject();

        uiMediator.raise(this, ItemsPositionSwapEvent.class, new ItemsPositionSwapEvent(a, b));
    }

    private void handleEquipmentSourceDrop(Payload payload){
        Item equippedItem = (Item) payload.getObject();

        uiMediator.raise(this, UnequipItemEvent.class, new UnequipItemEvent(equippedItem));
    }
}
