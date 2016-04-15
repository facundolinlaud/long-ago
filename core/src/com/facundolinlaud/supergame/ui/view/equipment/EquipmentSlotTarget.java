package com.facundolinlaud.supergame.ui.view.equipment;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.Slot;
import com.facundolinlaud.supergame.ui.view.cross.SlotSource;
import com.facundolinlaud.supergame.utils.WearType;
import com.facundolinlaud.supergame.utils.events.EquipItemEvent;
import com.facundolinlaud.supergame.utils.events.InventoryAndEquipmentItemSwapEvent;
import com.facundolinlaud.supergame.utils.mediator.Mediator;
import com.facundolinlaud.supergame.utils.mediator.Messenger;

/**
 * Created by facundo on 4/3/16.
 */
public class EquipmentSlotTarget extends Target implements Messenger {

    private Slot slot;
    private Mediator uiMediator;
    private WearType wearType;

    public EquipmentSlotTarget(EquipmentSlot slot, Mediator uiMediator, WearType wearType) {
        super(slot);

        this.slot = slot;
        this.uiMediator = uiMediator;
        this.wearType = wearType;
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
                System.out.println("from equipment to equipment");
                break;
        }
    }

    private void handleInventorySourceDrop(Payload payload){
        Item newItem = (Item) payload.getObject();

        Item alreadyEquippedItem = slot.getItem();

        if(isNewItemCompatibleWithSlot(newItem)){
            if(alreadyEquippedItem == null){
                uiMediator.raise(this, EquipItemEvent.class, new EquipItemEvent(newItem));
            } else {
                uiMediator.raise(this, InventoryAndEquipmentItemSwapEvent.class, new InventoryAndEquipmentItemSwapEvent(alreadyEquippedItem, newItem));
            }
        }
    }

    private boolean isNewItemCompatibleWithSlot(Item newItem) {
        return newItem.isEquipable() && wearType.equals(newItem.getEquipable().getWearType());
    }
}
