package com.facundolinlaud.supergame.ui.view.equipment;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.Slot;
import com.facundolinlaud.supergame.ui.view.cross.SlotSource;
import com.facundolinlaud.supergame.utils.Messages;
import com.facundolinlaud.supergame.utils.events.EquipItemEvent;
import com.facundolinlaud.supergame.utils.events.InventoryAndEquipmentItemsSwapEvent;

/**
 * Created by facundo on 4/3/16.
 */
public class EquipmentSlotTarget extends Target {

    private Slot<Item> slot;
    private EquipSlot equipSlot;
    private MessageDispatcher messageDispatcher;

    public EquipmentSlotTarget(EquipmentSlot slot, EquipSlot equipSlot) {
        super(slot);

        this.slot = slot;
        this.equipSlot = equipSlot;
        this.messageDispatcher = MessageManager.getInstance();
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
                broadcastInventorySourceDrop(payload);
                break;
            case EQUIPMENT_SLOT:
                System.out.println("from equipment to equipment");
                break;
        }
    }

    private void broadcastInventorySourceDrop(Payload payload){
        Item newItem = (Item) payload.getObject();

        Item alreadyEquippedItem = slot.getContent();

        if(isNewItemCompatibleWithSlot(newItem)){
            if(alreadyEquippedItem == null){
                messageDispatcher.dispatchMessage(Messages.ITEM_EQUIPPED, new EquipItemEvent(newItem));
            } else {
                messageDispatcher.dispatchMessage(Messages.INVENTORY_AND_EQUIMENT_ITEMS_SWAPPED,
                        new InventoryAndEquipmentItemsSwapEvent(alreadyEquippedItem, newItem));
            }
        }
    }

    private boolean isNewItemCompatibleWithSlot(Item newItem) {
        return newItem.isEquipable() && equipSlot.equals(newItem.getEquipable().getEquipSlot());
    }
}
