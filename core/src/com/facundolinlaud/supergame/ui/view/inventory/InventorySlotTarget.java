package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.Slot;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.ItemSlotSource;
import com.facundolinlaud.supergame.utils.events.ItemsPositionSwapEvent;
import com.facundolinlaud.supergame.utils.events.Messages;
import com.facundolinlaud.supergame.utils.events.UnequipItemEvent;

/**
 * Created by facundo on 3/30/16.
 */
public class InventorySlotTarget extends Target {

    private MessageDispatcher messageDispatcher;
    private Slot<Item> slot;

    public InventorySlotTarget(InventorySlot slot) {
        super(slot);

        this.slot = slot;
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        ItemSlotSource slotSource = (ItemSlotSource) source;

        switch(slotSource.getSlotType()){
            case INVENTORY_SLOT:
                broadcastInventorySourceDrop(payload);
                break;
            case EQUIPMENT_SLOT:
                broadcastEquipmentSourceDrop(payload);
                break;
        }
    }

    private void broadcastInventorySourceDrop(Payload payload) {
        Item a = slot.getContent();
        Item b = (Item) payload.getObject();

        if(a == null)
            return;

        if(a.getInvented().getPositionInBag() == b.getInvented().getPositionInBag())
            return;

        messageDispatcher.dispatchMessage(Messages.ITEMS_IN_INVENTORY_SWAPPED, new ItemsPositionSwapEvent(a, b));
    }

    private void broadcastEquipmentSourceDrop(Payload payload){
        Item equippedItem = (Item) payload.getObject();
        messageDispatcher.dispatchMessage(Messages.ITEM_UNEQUIPPED, new UnequipItemEvent(equippedItem));
    }
}
