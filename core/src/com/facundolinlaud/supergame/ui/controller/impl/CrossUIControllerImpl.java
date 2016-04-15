package com.facundolinlaud.supergame.ui.controller.impl;

import com.facundolinlaud.supergame.ui.controller.CrossUIController;
import com.facundolinlaud.supergame.utils.events.Event;
import com.facundolinlaud.supergame.utils.events.InventoryAndEquipmentItemSwapEvent;

/**
 * Created by facundo on 4/2/16.
 */
public class CrossUIControllerImpl implements CrossUIController {

    @Override
    public void handle(Event event) {
        onInventoryAndEquipmentItemSwap((InventoryAndEquipmentItemSwapEvent) event);
    }

    private void onInventoryAndEquipmentItemSwap(InventoryAndEquipmentItemSwapEvent event){
        System.out.println(event);
    }
}
