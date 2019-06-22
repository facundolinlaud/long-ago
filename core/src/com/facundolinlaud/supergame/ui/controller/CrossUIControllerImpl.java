package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.utils.Messages;
import com.facundolinlaud.supergame.utils.events.InventoryAndEquipmentItemsSwapEvent;

/**
 * Created by facundo on 4/2/16.
 */
public class CrossUIControllerImpl implements Telegraph {
    @Override
    public boolean handleMessage(Telegram msg) {
        switch(msg.message){
            case Messages.INVENTORY_AND_EQUIMENT_ITEMS_SWAPPED:
                onInventoryAndEquipmentItemsSwap((InventoryAndEquipmentItemsSwapEvent) msg.extraInfo);
                break;
        }

        return false;
    }

    private void onInventoryAndEquipmentItemsSwap(InventoryAndEquipmentItemsSwapEvent event){
        System.out.println(event);
    }
}
