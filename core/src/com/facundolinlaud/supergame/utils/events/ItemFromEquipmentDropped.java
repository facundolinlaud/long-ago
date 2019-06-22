package com.facundolinlaud.supergame.utils.events;

import com.facundolinlaud.supergame.ui.model.Item;

public class ItemFromEquipmentDropped extends Event {
    private Item item;

    public ItemFromEquipmentDropped(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
