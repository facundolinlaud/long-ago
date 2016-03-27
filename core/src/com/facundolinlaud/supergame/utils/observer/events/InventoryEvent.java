package com.facundolinlaud.supergame.utils.observer.events;

/**
 * Created by facundo on 3/27/16.
 */
public class InventoryEvent extends Event {
    private int selectedItem;

    public InventoryEvent(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getSelectedItem() {
        return selectedItem;
    }
}
